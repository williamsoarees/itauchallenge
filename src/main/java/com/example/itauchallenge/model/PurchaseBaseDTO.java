package com.example.itauchallenge.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
@Valid
public class PurchaseBaseDTO {

	@NotNull
	@Positive
	private Double value;

	private Date date = new Date();

	@NotBlank
	private String establishment;

	@NotNull
	private PurchaseType purchaseType;

}
