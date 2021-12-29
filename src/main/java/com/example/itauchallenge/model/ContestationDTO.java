package com.example.itauchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContestationDTO {

	private Integer id;
	private PurchaseBaseDTO purchase;
	private String protocol;

}
