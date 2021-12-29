package com.example.itauchallenge.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itauchallenge.entity.CardEntity;
import com.example.itauchallenge.entity.ContestationEntity;
import com.example.itauchallenge.entity.PurchaseEntity;
import com.example.itauchallenge.exceptions.CardException;
import com.example.itauchallenge.exceptions.PurchaseException;
import com.example.itauchallenge.model.ContestationDTO;
import com.example.itauchallenge.model.PurchaseDTO;
import com.example.itauchallenge.repository.CardRepository;
import com.example.itauchallenge.repository.ContestationRepository;
import com.example.itauchallenge.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private ContestationRepository contestationRepository;

	@Autowired
	private ModelMapper mapper;

	public void createPurchase(PurchaseDTO purchaseDTO) {
		Optional<CardEntity> cardEntity = cardRepository.findByNumberAndExpirationDateAndCvv(
				purchaseDTO.getCard().getNumber(), purchaseDTO.getCard().getExpirationDate(),
				purchaseDTO.getCard().getCvv());

		if (!cardEntity.isPresent()) {
			throw new CardException("The card is invalid!");
		}

		PurchaseEntity purchaseEntity = mapper.map(purchaseDTO, PurchaseEntity.class);

		purchaseEntity.setCard(cardEntity.get());

		purchaseRepository.save(purchaseEntity);
	}

	public ContestationDTO createContestation(Integer purchaseId) {
		Optional<PurchaseEntity> purchaseEntity = purchaseRepository.findById(purchaseId);

		if (!purchaseEntity.isPresent()) {
			throw new PurchaseException("There is no purchase with this identifier.");
		}

		if (purchaseEntity.get().isContested()) {
			throw new PurchaseException("This purchase has already been contested");
		}

		ContestationEntity contestationEntity = new ContestationEntity();
		contestationEntity.setPurchase(purchaseEntity.get());
		purchaseEntity.get().setContested(true);

		purchaseRepository.save(purchaseEntity.get());

		return mapper.map(contestationRepository.save(contestationEntity), ContestationDTO.class);

	}

}
