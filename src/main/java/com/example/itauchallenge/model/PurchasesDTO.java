package com.example.itauchallenge.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class PurchasesDTO {

	@Getter
	private List<PurchaseDTO> purchases = new ArrayList<>();
	
	public void addPurchase(PurchaseDTO purchaseDTO) {
		purchases.add(purchaseDTO);
	}
}
