package com.example.itauchallenge.model;

import java.util.ArrayList;
import java.util.List;

public class PurchasesDTO {

	private List<PurchaseDTO> purchases = new ArrayList<>();
	
	public void addPurchase(PurchaseDTO purchaseDTO) {
		purchases.add(purchaseDTO);
	}
}
