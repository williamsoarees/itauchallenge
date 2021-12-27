package com.example.itauchallenge.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PurchaseDTO {

	@NotNull
	private double value;

	@NotNull
	private LocalDateTime date = LocalDateTime.now();

	@NotBlank
	private String establishment;

	@NotNull
	private PurchaseType purchaseType;
	
	@NotNull
	private CardDTO card;
}
