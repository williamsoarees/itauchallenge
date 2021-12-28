package com.example.itauchallenge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.itauchallenge.entity.PurchaseEntity;

public interface PurchaseRepository extends CrudRepository<PurchaseEntity, Integer> {

	List<PurchaseEntity> findByCardId(Integer id);
	
	List<PurchaseEntity> findByCardIdAndContested(Integer id, boolean contested);

}
