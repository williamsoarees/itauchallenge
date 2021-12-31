package com.example.itauchallenge.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.itauchallenge.entity.PurchaseEntity;

public interface PurchaseRepository extends CrudRepository<PurchaseEntity, Integer> {

	List<PurchaseEntity> findByCardIdAndContested(Integer id, boolean contested);
	
	List<PurchaseEntity> findByCardIdAndDateBetween(Integer id, Date start, Date end);

}
