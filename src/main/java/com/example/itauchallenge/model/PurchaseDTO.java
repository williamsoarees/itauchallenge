package com.example.itauchallenge.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Valid
public class PurchaseDTO extends PurchaseBaseDTO{

	@Getter @Setter
	@NotNull
	private CardDTO card;
}
