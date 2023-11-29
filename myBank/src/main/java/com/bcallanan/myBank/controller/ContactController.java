package com.bcallanan.myBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	@GetMapping( value = { "/Notices","/notices" })
	public String getNotices() {
		return "Hello Welcome to spring rest application 'MyBank'";
	}
	
}
