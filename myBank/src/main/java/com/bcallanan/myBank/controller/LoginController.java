package com.bcallanan.myBank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.CustomerRepository;

import jakarta.validation.Valid;

@RestController
public class LoginController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping( "/register")
	public ResponseEntity<String> registerUser( @RequestBody  @Valid Customer customer ) {
		
		Customer newCustomer;
		ResponseEntity< String > response = null;
		
		try {
			
			if ( customer.getRegistrationToken() == null ||
					! customer.getRegistrationToken().equals( "12345" )) {
				throw new BadCredentialsException( "The registration token is invalid." );
			}
			String hashedPassword = passwordEncoder.encode( customer.getPwd() );
			customer.setPwd(hashedPassword);
			
			newCustomer = customerRepository.save( customer );
			if ( newCustomer.getCustomerId() > 0 ) {
				response = ResponseEntity
					.status( HttpStatus.CREATED)
					.body( "The User was successfully created");
			}
		} catch (Exception e) {
			if ( e.getCause() != null ) {
				
				if ( e instanceof DataIntegrityViolationException) {
					
					if ( ((DataIntegrityViolationException) e).getMessage()
					   .toLowerCase()
					   .contains( "already exists") ) {
						response = ResponseEntity
								.status( HttpStatus.CONFLICT )
								.body( "Error: An account with that name already exists.");

						return response;
					}
				}

				response = ResponseEntity
						.status( HttpStatus.INTERNAL_SERVER_ERROR )
						.body( "The User failed to be created: " +
								e.getCause().getMessage());
			}
			else {
				response = ResponseEntity
				.status( HttpStatus.INTERNAL_SERVER_ERROR )
				.body( "The User failed to be created: " +
				e.getMessage());
				
			}
		}
		
		return response;
	}
}
