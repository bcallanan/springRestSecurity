package com.bcallanan.myBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.AccountTransaction;
import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.AccountTransactionRepository;
import com.bcallanan.myBank.jpa.CustomerRepository;

@RestController
public class BalanceController {

	@Autowired
	AccountTransactionRepository accountTransactionRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Value("${spring.security.config.jwt}")
	private Boolean isJWTEnabled; 

	@GetMapping( value = { "/myBalance","/mybalance" })
	public List<AccountTransaction> getBalanceDetails( @RequestParam String customerId ) {
		
		Integer id = null;
		if ( ! isJWTEnabled ) {
			// here the id is going to be the email address. so cast to a string,
			// get the customer -> get the id -> then get the account
			List<Customer> customers = customerRepository.findByEmailAddress( (String) customerId);
			
			if ( customers != null && ! customers.isEmpty() ) {
				id = customers.get(0).getCustomerId();
			}
			else { 
				return null;
			}
		}
		else {
			id = Integer.valueOf( (String) customerId );
		}

		List<AccountTransaction> accountTransactions = accountTransactionRepository
				.findByCustomerIdOrderByTransactionDateDesc( id );
		
		return accountTransactions;
	}
}
