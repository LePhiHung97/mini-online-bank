package com.hunglp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hunglp.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long>{
	
	@Query("SELECT sa FROM saving_account sa WHERE sa.account_number = :accountNumber")
	SavingsAccount findByAccountNumber(int accountNumber);
}
