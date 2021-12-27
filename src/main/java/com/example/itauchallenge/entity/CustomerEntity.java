package com.example.itauchallenge.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "email", nullable = false)
	private String email;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<CardEntity> cards;
}
