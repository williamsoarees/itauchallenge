package com.example.itauchallenge.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.itauchallenge.entity.CardEntity;

public interface CardRepository extends CrudRepository<CardEntity, Integer>{

	Optional<CardEntity> findByNumberAndExpirationDateAndCvv(String number, String expirationDate, String cvv);
}
