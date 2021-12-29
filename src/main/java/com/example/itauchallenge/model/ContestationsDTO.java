package com.example.itauchallenge.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ContestationsDTO {

	@Getter
	private List<CardContestationsDTO> contestations = new ArrayList<>();
	
	public void addContestations(CardContestationsDTO cardContestationsDTO) {
		this.contestations.add(cardContestationsDTO);
	}

}
