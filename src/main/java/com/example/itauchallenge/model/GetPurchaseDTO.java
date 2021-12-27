package com.example.itauchallenge.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class GetPurchaseDTO {
	
	private Integer id;

	private double value;

	@Getter(AccessLevel.NONE)
	private LocalDateTime date;

	private String establishment;

	private PurchaseType purchaseType;
	
	public String getDate() {
		return this.date.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
	}
}
