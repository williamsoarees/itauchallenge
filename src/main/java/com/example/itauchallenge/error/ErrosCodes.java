package com.example.itauchallenge.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrosCodes {

	INTERNAL_SERVER_ERROR("Internal server error"), INVALID_REQUEST("Invalid request"),
	PURCHASE_ERROR("There was a problem with the purchase"), CUSTOMER_ERROR("There was a problem with the customer"),
	CARD_ERROR("There was a problem with the card");

	private final String mensagem;
}
