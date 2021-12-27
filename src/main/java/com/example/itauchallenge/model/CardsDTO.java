package com.example.itauchallenge.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class CardsDTO {

	@Getter
	private List<CardDTO> cards = new ArrayList<>();
	
	public void addCard(CardDTO card) {
		cards.add(card);
	}
}
