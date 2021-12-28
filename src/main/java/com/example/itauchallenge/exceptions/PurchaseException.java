package com.example.itauchallenge.exceptions;

public class PurchaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PurchaseException(String str) {
		super(str);
	}
}
