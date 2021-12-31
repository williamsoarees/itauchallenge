package com.example.itauchallenge.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.itauchallenge.model.PurchaseType;

import lombok.Data;

@Data
@Entity
@Table(name = "purchase")
public class PurchaseEntity {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "value", nullable = false)
	private double value;

	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Column(name = "establishmentv", nullable = false)
	private String establishment;

	@Column(name = "purchase_type", nullable = false)
	private PurchaseType purchaseType;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	private CardEntity card;
	
	@Column(name = "contested")
	private boolean contested;
	
}
