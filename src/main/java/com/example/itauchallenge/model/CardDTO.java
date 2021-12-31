package com.example.itauchallenge.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CardDTO {
	
//	private Integer id;

	@NotBlank
	private String number;
	
	@NotBlank
	private String expirationDate;
	
	@NotBlank
	private String cvv;
}
