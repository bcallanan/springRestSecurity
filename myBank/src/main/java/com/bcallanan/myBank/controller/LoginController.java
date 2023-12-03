package com.bcallanan.myBank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.CustomerRepository;

@RestController
public class LoginController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping( "/register")
	public ResponseEntity<String> registerUser( @RequestBody Customer customer ) {
		
		Customer newCustomer;
		ResponseEntity< String > response = null;
		
		try {
			String hashedPassword = passwordEncoder.encode( customer.getPwd() );
			customer.setPwd(hashedPassword);
			
			newCustomer = customerRepository.save( customer );
			if ( newCustomer.getCustomerId() > 0 ) {
				response = ResponseEntity
					.status( HttpStatus.CREATED)
					.body( "The User was successfully created");
			}
		} catch (Exception e) {
			response = ResponseEntity
			.status( HttpStatus.INTERNAL_SERVER_ERROR )
			.body( "The User failed to be created: \n" +
			e.getCause().getMessage());
		}
		
		return response;
	}
}
