package com.example.itauchallenge.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itauchallenge.entity.CardEntity;
import com.example.itauchallenge.entity.PurchaseEntity;
import com.example.itauchallenge.model.PurchaseDTO;
import com.example.itauchallenge.repository.CardRepository;
import com.example.itauchallenge.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private ModelMapper mapper;

	public void createPurchase(PurchaseDTO purchaseDTO) {
		Optional<CardEntity> cardEntity = cardRepository.findByNumberAndExpirationDateAndCvv(purchaseDTO.getCard().getNumber(),
				purchaseDTO.getCard().getExpirationDate(), purchaseDTO.getCard().getCvv());
		
		PurchaseEntity purchaseEntity = mapper.map(purchaseDTO, PurchaseEntity.class);
		
		purchaseEntity.setCard(cardEntity.get());
		
		purchaseRepository.save(purchaseEntity);
	}

}
