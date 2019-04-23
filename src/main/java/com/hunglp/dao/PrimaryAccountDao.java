package com.hunglp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hunglp.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
	
	@Query("SELECT pa FROM PrimaryAccount pa WHERE pa.accountNumber = :accountNumber")
	PrimaryAccount findByAccountNumber(int accountNumber);
	
	
	
}
