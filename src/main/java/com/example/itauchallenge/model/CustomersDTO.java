package com.example.itauchallenge.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class CustomersDTO {

	@Getter
	private List<CustomerDTO> customers = new ArrayList<>();
	
	public void addCustomer(CustomerDTO customer) {
		customers.add(customer);
	}
}
