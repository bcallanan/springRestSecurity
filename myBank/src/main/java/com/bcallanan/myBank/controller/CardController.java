package com.bcallanan.myBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

	@GetMapping( value = { "/myCards","/mycards" })
	public String getCardDetails() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
}
