package com.hunglp.service;

import com.hunglp.domain.PrimaryAccount;
import com.hunglp.domain.SavingsAccount;

public interface AccountService {
	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
}
