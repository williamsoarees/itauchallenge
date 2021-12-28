package com.example.itauchallenge.model;

import lombok.Data;

@Data
public class ContestationDTO {

	private Integer id;
	private PurchaseBaseDTO purchase;
	private String protocol;

}
