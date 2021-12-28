package com.example.itauchallenge.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itauchallenge.model.ContestationDTO;
import com.example.itauchallenge.model.PurchaseDTO;
import com.example.itauchallenge.service.PurchaseService;

@RestController
@RequestMapping("itauchallenge")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@PostMapping("/v1/purchases")
	public ResponseEntity<Void> createPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) {
		purchaseService.createPurchase(purchaseDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/v1/purchases/{purchase_id}/contestations")
	public ResponseEntity<ContestationDTO> createContestation(@PathVariable(name = "purchase_id") @Valid Integer purchaseId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.createContestation(purchaseId));
	}

}
