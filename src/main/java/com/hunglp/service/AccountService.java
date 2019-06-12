package com.hunglp.service;



import com.hunglp.domain.PrimaryAccount;
import com.hunglp.domain.SavingsAccount;
import com.hunglp.domain.User;

public interface AccountService {
	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	void withdraw(String accountType, double amount, User user);
	void deposit(String accountType, double amount, User user);
	
	
}
