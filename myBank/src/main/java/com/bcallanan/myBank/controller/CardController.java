package com.bcallanan.myBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Card;
import com.bcallanan.myBank.jpa.CardRepository;

@RestController
public class CardController {

	@Autowired
	CardRepository cardRepository;
	
	@GetMapping( value = { "/myCards", "/mycards" })
	public List<Card> getCardDetails( @RequestParam int id ) {
		
		List<Card> cards = cardRepository.findByCustomerId( id );
		return cards;
	}
}
