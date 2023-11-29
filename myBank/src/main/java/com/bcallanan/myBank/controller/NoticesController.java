package com.bcallanan.myBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

	@GetMapping( value = { "/Contact","/contact" })
	public String getContactsDetails() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
	
	@GetMapping( value = { "/ContactRequest","/contactRequest" })
	public String saveContactInquiryDetails() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
}
