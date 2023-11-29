package com.bcallanan.myBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

	@GetMapping( value = { "/myLoans","/myloans" })
	public String getLoanDetails() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
}
