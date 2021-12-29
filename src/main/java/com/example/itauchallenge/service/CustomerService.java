package com.example.itauchallenge.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itauchallenge.entity.CardEntity;
import com.example.itauchallenge.entity.ContestationEntity;
import com.example.itauchallenge.entity.CustomerEntity;
import com.example.itauchallenge.entity.PurchaseEntity;
import com.example.itauchallenge.exceptions.CustomerException;
import com.example.itauchallenge.model.CardContestationsDTO;
import com.example.itauchallenge.model.CardDTO;
import com.example.itauchallenge.model.CardPurchasesDTO;
import com.example.itauchallenge.model.CardsDTO;
import com.example.itauchallenge.model.ContestationDTO;
import com.example.itauchallenge.model.ContestationsDTO;
import com.example.itauchallenge.model.CustomerCardsPurchasesDTO;
import com.example.itauchallenge.model.CustomerDTO;
import com.example.itauchallenge.model.CustomersDTO;
import com.example.itauchallenge.model.GetPurchaseDTO;
import com.example.itauchallenge.model.PurchaseBaseDTO;
import com.example.itauchallenge.repository.ContestationRepository;
import com.example.itauchallenge.repository.CustomerRepository;
import com.example.itauchallenge.repository.PurchaseRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private ContestationRepository contestationRepository;

	@Autowired
	private ModelMapper mapper;

	public void createCustomer(CustomerDTO customerDTO) {

		if (customerRepository.findByCpf(customerDTO.getCpf()).isPresent()) {
			throw new CustomerException("This document number is already registered!");
		}

		if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
			throw new CustomerException("This email is already registered!");
		}

		customerRepository.save(mapper.map(customerDTO, CustomerEntity.class));
	}

	public CustomersDTO getCustomers() {
		CustomersDTO customers = new CustomersDTO();

		for (CustomerEntity customerEntity : customerRepository.findAll()) {
			customers.addCustomer(mapper.map(customerEntity, CustomerDTO.class));
		}

		return customers;
	}

	public CardDTO createCard(String CustomerCpf) {
		CustomerEntity customer = findCustomerByCpf(CustomerCpf);

		CardEntity cardEntity = createCard();
		customer.getCards().add(cardEntity);

		customerRepository.save(customer);

		return mapper.map(cardEntity, CardDTO.class);
	}

	public CardsDTO getCards(String customerCpf) {
		CardsDTO cards = new CardsDTO();

		for (CardEntity cardEntity : findCustomerByCpf(customerCpf).getCards()) {
			cards.addCard(mapper.map(cardEntity, CardDTO.class));
		}

		return cards;
	}

	public ContestationsDTO getConstestations(String customerCpf) {

		CustomerEntity customerEntity = findCustomerByCpf(customerCpf);

		ContestationsDTO contestationsDTO = new ContestationsDTO();

		for (CardEntity cardEntity : customerEntity.getCards()) {

			List<ContestationDTO> purchaseContestations = new ArrayList<>();

			for (PurchaseEntity purchaseEntity : purchaseRepository.findByCardIdAndContested(cardEntity.getId(),
					true)) {

				ContestationEntity contestationEntity = contestationRepository.findByPurchaseId(purchaseEntity.getId());

				purchaseContestations.add(ContestationDTO.builder().id(purchaseEntity.getId())
						.purchase(mapper.map(contestationEntity.getPurchase(), PurchaseBaseDTO.class))
						.protocol(contestationEntity.getProtocol()).build());
			}

			CardContestationsDTO cardContestationsDTO = new CardContestationsDTO();
			cardContestationsDTO.setCard(cardEntity.getNumber());
			cardContestationsDTO.setPurchases(purchaseContestations);

			contestationsDTO.addContestations(cardContestationsDTO);
		}
		// PEGAR TODAS AS CONTESTAÇÕES POR CARTÃO
		// Colocar o swagger
		return contestationsDTO;
	}

	private CardEntity createCard() {
		CardEntity cardEntity = new CardEntity();

		Random random = new Random();

		cardEntity.setNumber(generatedeCard());
		cardEntity.setExpirationDate(LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
		cardEntity.setCvv(
				replaceString(Arrays.asList(random.nextInt(9), random.nextInt(9), random.nextInt(9)).toString()));
		return cardEntity;
	}

	public CustomerCardsPurchasesDTO customerComplete(String customerCpf) {
		CustomerEntity customerEntity = findCustomerByCpf(customerCpf);

		CustomerCardsPurchasesDTO customer = mapper.map(customerEntity, CustomerCardsPurchasesDTO.class);

		List<CardPurchasesDTO> cards = new ArrayList<>();

		for (CardEntity cardEntity : customerEntity.getCards()) {
			CardPurchasesDTO card = new CardPurchasesDTO();
			card.setNumber(cardEntity.getNumber());

			List<GetPurchaseDTO> purchases = new ArrayList<>();

			for (PurchaseEntity purchaseEntity : purchaseRepository.findByCardIdAndContested(cardEntity.getId(),
					false)) {
				purchases.add(mapper.map(purchaseEntity, GetPurchaseDTO.class));
				card.setPurchases(purchases);
			}

			card.setPurchases(purchases);
			cards.add(card);
		}

		customer.setCards(cards);

		return customer;
	}

	private String generatedeCard() {
		Random random = new Random();

		List<Integer> cardNumber = new ArrayList<>();

		// Para o banco itaú os cartões são visa ou master, depois gerar cartões assim

		for (int i = 0; i < 15; i++) {
			cardNumber.add(random.nextInt(9));
		}

		List<Integer> cardDigit = new ArrayList<>(cardNumber);

		for (int i = 0; i < 15; i += 2) {
			cardDigit.set(i, cardDigit.get(i) * 2);
		}

		for (int i = 0; i < 15; i++) {
			if (cardDigit.get(i) > 9) {
				cardDigit.set(i, cardDigit.get(i) - 9);
			}
		}

		int sum = cardDigit.stream().mapToInt(n -> IntStream.of(n).sum()).sum();

		cardNumber.add((sum % 10 == 0) ? (0) : (10 - sum % 10));

		return replaceString(cardNumber.toString());
	}

	private String replaceString(String str) {
		return str.replace(",", "").replace(" ", "").replace("[", "").replace("]", "");
	}

	private CustomerEntity findCustomerByCpf(String customerCpf) {
		Optional<CustomerEntity> customer = customerRepository.findByCpf(customerCpf);

		if (!customer.isPresent()) {
			throw new CustomerException("There is no user with this document number");
		}

		return customer.get();
	}

}
