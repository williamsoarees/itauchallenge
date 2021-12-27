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
import com.example.itauchallenge.entity.CustomerEntity;
import com.example.itauchallenge.entity.PurchaseEntity;
import com.example.itauchallenge.model.CardDTO;
import com.example.itauchallenge.model.CardPurchasesDTO;
import com.example.itauchallenge.model.CardsDTO;
import com.example.itauchallenge.model.CustomerCardsPurchasesDTO;
import com.example.itauchallenge.model.CustomerDTO;
import com.example.itauchallenge.model.CustomersDTO;
import com.example.itauchallenge.model.GetPurchaseDTO;
import com.example.itauchallenge.repository.CustomerRepository;
import com.example.itauchallenge.repository.PurchaseRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private ModelMapper mapper;

	public void createCustomer(CustomerDTO customerDTO) {
		// adicionar try/catch
		// adicionar validações para nao cadastrar com cpf e email duplicado
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
		CustomerEntity customer = findCustomerById(CustomerCpf);

		CardEntity cardEntity = createCard();
		customer.getCards().add(cardEntity);

		customerRepository.save(customer);

		return mapper.map(cardEntity, CardDTO.class);
	}

	public CardsDTO getCards(String customerCpf) {
		CardsDTO cards = new CardsDTO();

		for (CardEntity cardEntity : findCustomerById(customerCpf).getCards()) {
			cards.addCard(mapper.map(cardEntity, CardDTO.class));
		}

		return cards;
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
		CustomerEntity customerEntity = findCustomerById(customerCpf);

		CustomerCardsPurchasesDTO customer = mapper.map(customerEntity, CustomerCardsPurchasesDTO.class);
		
		List<CardPurchasesDTO> cards = new ArrayList<>();
		
		for (CardEntity cardEntity : customerEntity.getCards()) {
			CardPurchasesDTO card = new CardPurchasesDTO();
			card.setNumber(cardEntity.getNumber());
			
			List<GetPurchaseDTO> purchases = new ArrayList<>();
			
			for (PurchaseEntity purchaseEntity : purchaseRepository.findByCardId(cardEntity.getId())) {
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
	
	private CustomerEntity findCustomerById(String customerCpf) {
		Optional<CustomerEntity> customer = customerRepository.findByCpf(customerCpf);

		if (!customer.isPresent()) {
			// retornar erro
		}

		return customer.get();
	}

}
