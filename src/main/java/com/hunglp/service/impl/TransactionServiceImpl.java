package com.hunglp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hunglp.dao.PrimaryTransactionDao;
import com.hunglp.dao.SavingsTransactionDao;
import com.hunglp.domain.PrimaryTransaction;
import com.hunglp.domain.SavingsTransaction;
import com.hunglp.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;
	
	@Autowired
	private SavingsTransactionDao savingsTransactionDao;
	
	
	@Override
	public void saveDepositPrimaryTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	@Override
	public void saveDepositSavingsTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}

	@Override
	public void saveWithdrawPrimaryTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
		
	}

	@Override
	public void saveWithdrawSavingsTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}
	
}
