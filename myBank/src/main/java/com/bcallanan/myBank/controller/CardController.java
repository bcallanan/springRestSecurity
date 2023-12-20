package com.bcallanan.myBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Card;
import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.CardRepository;
import com.bcallanan.myBank.jpa.CustomerRepository;

@RestController
public class CardController {

	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Value("${spring.security.config.jwt}")
	private Boolean isJWTEnabled; 

	@GetMapping( value = { "/myCards", "/mycards" })
	public List<Card> getCardDetails( @RequestParam Object id ) {

		Integer customerId = null;
		if ( ! isJWTEnabled ) {
			// here the id is going to be the email address. so cast to a string,
			// get the customer -> get the id -> then get the account
			List<Customer> customers = customerRepository.findByEmailAddress( (String) id);
			
			if ( customers != null && ! customers.isEmpty() ) {
				customerId = customers.get(0).getCustomerId();
			}
			else { 
				return null;
			}
		}
		else {
			customerId = (Integer) id;
		}

		List<Card> cards = cardRepository.findByCustomerId( customerId );
		return cards;
	}
}
