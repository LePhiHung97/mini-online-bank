package com.hunglp.dao;

import org.springframework.data.repository.CrudRepository;

import com.hunglp.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long>{
	
}
