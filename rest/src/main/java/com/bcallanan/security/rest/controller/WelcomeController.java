package com.bcallanan.security.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping( value = { "/welcome","/*" })
	public String welcome() {
		return "Hello Welcome to my spring rest application";
	}
}
