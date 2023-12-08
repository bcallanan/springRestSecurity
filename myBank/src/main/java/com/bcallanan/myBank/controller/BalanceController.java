package com.bcallanan.myBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.AccountTransaction;
import com.bcallanan.myBank.jpa.AccountTransactionRepository;

@RestController
public class BalanceController {

	@Autowired
	AccountTransactionRepository accountTransactionRepository;
	
	@GetMapping( value = { "/myBalance","/mybalance" })
	public List<AccountTransaction> getBalanceDetails( @RequestParam Integer customerId ) {
		
		List<AccountTransaction> accountTransactions = accountTransactionRepository
				.findByCustomerIdOrderByTransactionDateDesc( customerId );
		
		return accountTransactions;
	}
}
