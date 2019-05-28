package com.hunglp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hunglp.domain.PrimaryTransaction;
import com.hunglp.domain.SavingsTransaction;

import com.hunglp.domain.User;
import com.hunglp.service.UserService;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/primaryAccount")
	public String primaryAccount(Model model,Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<PrimaryTransaction> lstTransactions = user.getPrimaryAccount().getPrimaryTransactionList();
		
		model.addAttribute("primaryAccount", user.getPrimaryAccount());
		model.addAttribute("lstPrimaryTransaction", lstTransactions);
		return "primaryAcccount";	
	}
	
	@RequestMapping("/savingAccount")
	public String savingTransaction(Model model,Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<SavingsTransaction> lstTransactions = user.getSavingsAccount().getSavingsTransactionList();
		
		model.addAttribute("savingAccount", user.getSavingsAccount());
		model.addAttribute("lstSavingTransaction", lstTransactions);
		return "savingAccount";	
	}
}
