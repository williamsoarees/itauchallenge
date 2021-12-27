package com.example.itauchallenge.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "contestation")
public class ContestationEntity {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne
	private PurchaseEntity purchase;

	private String protocolo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMuuuu"))
			+ String.valueOf(Math.floor(Math.random() * 60000)).replace(".0", "");
}
