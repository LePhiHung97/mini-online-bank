package com.hunglp.dao;

import org.springframework.data.repository.CrudRepository;

import com.hunglp.domain.SavingsTransaction;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction,Long> {
	
}
