package com.hunglp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hunglp.domain.PrimaryTransaction;
import com.hunglp.domain.SavingsTransaction;
import com.hunglp.domain.User;
import com.hunglp.service.AccountService;
import com.hunglp.service.UserService;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
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
	@RequestMapping(value = "/deposit",method = RequestMethod.GET)
	public String deposit(Model model) {
		model.addAttribute("accountType", "");
		model.addAttribute("amount","");
		return "deposit";
	}
	
	@RequestMapping(value="/deposit",method=RequestMethod.POST)
	public String deposit(@ModelAttribute("amount")String amount,@ModelAttribute("accountType") String accountType,HttpSession session) {
		User user = (User) session.getAttribute("user");
		accountService.deposit(accountType, Double.parseDouble(amount), user);
		return "home";	
	}
	
	@RequestMapping(value="/withdraw",method= RequestMethod.GET)
	public String withdraw(Model model) {
		model.addAttribute("accountType","");
		model.addAttribute("amount", "");
		return "withdraw";
	}
	
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	public String withdraw(@ModelAttribute("amount")String amount,@ModelAttribute("accountType") String accountType,HttpSession session) {
		User user = (User) session.getAttribute("user");
		accountService.withdraw(accountType, Double.parseDouble(amount), user);
		return "home";	
	}
	
	
}
