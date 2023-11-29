package com.bcallanan.myBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	
	@GetMapping( value = { "/myAccount","/myaccount" })
	public String getAccountDetails() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
}
