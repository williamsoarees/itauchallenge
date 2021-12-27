package com.example.itauchallenge.model;

import java.util.List;

import lombok.Data;

@Data
public class CardPurchasesDTO {
	
	private String number;
	private List<GetPurchaseDTO> purchases;

}
