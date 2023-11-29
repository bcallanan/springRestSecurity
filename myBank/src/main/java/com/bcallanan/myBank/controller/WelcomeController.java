package com.bcallanan.myBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping( value = { "/welcome","/*" })
	public String welcome() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
}
