package com.example.itauchallenge.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PurchaseBaseDTO {

	@NotNull
	private double value;

	private LocalDateTime date;

	@NotBlank
	private String establishment;

	@NotNull
	private PurchaseType purchaseType;
	
	public String getDate() {
		return this.date.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
	}

}
