package com.hunglp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hunglp.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long>{
	
	@Query("SELECT sa FROM SavingsAccount sa WHERE sa.accountNumber = :accountNumber")
	SavingsAccount findByAccountNumber(int accountNumber);
}
