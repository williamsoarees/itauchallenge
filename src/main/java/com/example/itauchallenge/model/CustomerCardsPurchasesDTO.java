package com.example.itauchallenge.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CustomerCardsPurchasesDTO extends CustomerDTO {

	@Getter @Setter
	private List<CardPurchasesDTO> cards;
}
