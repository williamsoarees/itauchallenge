package com.example.itauchallenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "card")
public class CardEntity {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "number", nullable = false)
	private String number;

	@Column(name = "expirationDate", nullable = false)
	private String expirationDate;

	@Column(name = "cvv", nullable = false)
	private String cvv;
}
