package com.bcallanan.myBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

	@GetMapping( value = { "/myBalance","/mybalance" })
	public String getBalanceDetails() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
}
