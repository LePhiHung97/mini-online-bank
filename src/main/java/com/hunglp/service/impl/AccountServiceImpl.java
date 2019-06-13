package com.hunglp.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hunglp.dao.PrimaryAccountDao;
import com.hunglp.dao.SavingsAccountDao;
import com.hunglp.domain.PrimaryAccount;
import com.hunglp.domain.PrimaryTransaction;
import com.hunglp.domain.SavingsAccount;
import com.hunglp.domain.SavingsTransaction;
import com.hunglp.domain.User;
import com.hunglp.service.AccountService;
import com.hunglp.service.TransactionService;


@Service
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber = 11223145;

	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	@Autowired
	private TransactionService transactionService;

	@Override
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGenerate());

		primaryAccountDao.save(primaryAccount);
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	private int accountGenerate() {
		return ++nextAccountNumber;
	}

	@Override
	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGenerate());
		
		savingsAccountDao.save(savingsAccount);
		return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
	}

	@Override
	public void withdraw(String accountType, double amount, User user) {
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(), "Withdraw Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			transactionService.saveWithdrawPrimaryTransaction(primaryTransaction);
		}
		else if(accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
		    savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
		    savingsAccountDao.save(savingsAccount);
		    
		    SavingsTransaction savingsTransaction = new SavingsTransaction(new Date(), "Withdraw Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
		    transactionService.saveWithdrawSavingsTransaction(savingsTransaction);
		}	
	}

	@Override
	public void deposit(String accountType, double amount, User user) {
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(), "Deposit Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			transactionService.saveDepositPrimaryTransaction(primaryTransaction);
		}
		else if(accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
		    savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
		    savingsAccountDao.save(savingsAccount);
		    
		    SavingsTransaction savingsTransaction = new SavingsTransaction(new Date(), "Withdraw Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
		    transactionService.saveDepositSavingsTransaction(savingsTransaction);
		}
		
	}

}
