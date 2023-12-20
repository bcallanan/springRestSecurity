package com.bcallanan.myBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Account;
import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.AccountRepository;
import com.bcallanan.myBank.jpa.CustomerRepository;

@RestController
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Value("${spring.security.config.jwt}")
	private Boolean isJWTEnabled; 
	
	@GetMapping( value = { "/myAccount","/myaccount" })
	public Account getAccountDetails( @RequestParam Object id ) {
		
		Account account = null;
		Integer customerId = null;
		if ( ! isJWTEnabled ) {
			// here the id is going to be the email address. so cast to a string,
			// get the customer -> get the id -> then get the account
			List<Customer> customers = customerRepository.findByEmailAddress( (String) id);
			
			if ( customers != null && ! customers.isEmpty() ) {
				customerId = customers.get(0).getCustomerId();
			}
			else { 
				return null;
			}
		}
		else {
			customerId = (Integer) id;
		}

		account = accountRepository.findByCustomerId( customerId );
		return account;
	}
}
