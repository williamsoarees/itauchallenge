package com.example.itauchallenge.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class CustomerDTO {

	@NotBlank
	private String name;

	@CPF
	private String cpf;

	@Email
	private String email;
}
