package com.example.itauchallenge.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itauchallenge.model.CardDTO;
import com.example.itauchallenge.model.CardsDTO;
import com.example.itauchallenge.model.ContestationsDTO;
import com.example.itauchallenge.model.CustomerCardsPurchasesDTO;
import com.example.itauchallenge.model.CustomerDTO;
import com.example.itauchallenge.model.CustomersDTO;
import com.example.itauchallenge.service.CustomerService;

@RestController
@RequestMapping("itauchallenge")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/v1/customers")
	public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerDTO customer) {
		customerService.createCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/v1/customers")
	public ResponseEntity<CustomersDTO> getCustomers() {
		return ResponseEntity.ok(customerService.getCustomers());
	}

	@PostMapping("/v1/customers/{customer_cpf}/cards")
	public ResponseEntity<CardDTO> createCard(@PathVariable(name = "customer_cpf") String cpf) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCard(cpf));
	}

	@GetMapping("/v1/customers/{customer_cpf}/cards")
	public ResponseEntity<CardsDTO> getCard(@PathVariable(name = "customer_cpf") String cpf) {
		return ResponseEntity.ok(customerService.getCards(cpf));
	}

	@GetMapping("/v1/customers/{customer_cpf}/purchases")
	public ResponseEntity<CustomerCardsPurchasesDTO> getCustomerComplete(
			@PathVariable(name = "customer_cpf") String cpf) {
		return ResponseEntity.ok(customerService.customerComplete(cpf));
	}

	@GetMapping("/v1/customers/{customer_cpf}/contestations")
	public ResponseEntity<ContestationsDTO> getContestations(@PathVariable(name = "customer_cpf") String cpf) {
		return ResponseEntity.ok(customerService.getConstestations(cpf));
	}
}
