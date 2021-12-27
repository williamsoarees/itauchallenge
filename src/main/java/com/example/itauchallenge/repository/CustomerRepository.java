package com.example.itauchallenge.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.itauchallenge.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer>{

	Optional<CustomerEntity> findByCpf(String cpf);
}
