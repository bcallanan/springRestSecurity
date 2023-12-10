package com.bcallanan.myBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Account;
import com.bcallanan.myBank.jpa.AccountRepository;

@RestController
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping( value = { "/myAccount","/myaccount" })
	public Account getAccountDetails( @RequestParam Integer id ) {
		
		Account account = accountRepository.findByCustomerId( id );
		
		return account;
	}
}
