package com.bcallanan.myBank.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Contact;
import com.bcallanan.myBank.jpa.ContactRepository;

@RestController
public class ContactController {

	@Autowired
	ContactRepository contactRepository;
	
	@PostMapping( value = { "/contact", "/Contact" })
	public Contact getContactInquiryDetails( @RequestBody Contact contact ) {
		
		contact.setContactId( getServiceRequestNumber() );
		contact.setCreateDate( new Date( System.currentTimeMillis()) );
		
		return contactRepository.save( contact );
	}

	private String getServiceRequestNumber() {
		Random rand = new Random();
		int randomNumber = rand.nextInt( 999999999 - 9999 ) + 9999;

		return "SR" + randomNumber;
	}
}
