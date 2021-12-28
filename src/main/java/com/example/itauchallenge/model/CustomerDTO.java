package com.example.itauchallenge.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Valid
public class CustomerDTO {

	@NotBlank
	private String name;

	@NotNull
	@CPF
	private String cpf;

	@NotNull
	@Email(message = "Informe um e-mail válido!")
	private String email;
}
