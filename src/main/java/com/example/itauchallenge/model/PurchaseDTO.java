package com.example.itauchallenge.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class PurchaseDTO extends PurchaseBaseDTO{

	@Getter @Setter
	@NotNull
	private CardDTO card;
}
