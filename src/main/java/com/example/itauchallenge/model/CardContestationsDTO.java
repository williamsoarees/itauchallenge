package com.example.itauchallenge.model;

import java.util.List;

import lombok.Data;

@Data
public class CardContestationsDTO {

	private String card;
	private List<ContestationDTO> purchases;

}
