package com.hunglp.service;

import com.hunglp.domain.PrimaryTransaction;
import com.hunglp.domain.SavingsTransaction;

public interface TransactionService {
	void saveDepositPrimaryTransaction(PrimaryTransaction primaryTransaction);

	void saveDepositSavingsTransaction(SavingsTransaction savingsTransaction);

	void saveWithdrawPrimaryTransaction(PrimaryTransaction primaryTransaction);

	void saveWithdrawSavingsTransaction(SavingsTransaction savingsTransaction);
}
