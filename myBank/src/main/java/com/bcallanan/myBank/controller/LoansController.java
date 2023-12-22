package com.bcallanan.myBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.entity.Loan;
import com.bcallanan.myBank.jpa.CustomerRepository;
import com.bcallanan.myBank.jpa.LoanRepository;

@RestController
public class LoansController {

	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Value("${spring.security.config.jwt}")
	private Boolean isJWTEnabled; 

	@GetMapping( value = { "/myLoans","/myloans" })
	public List<Loan> getLoanDetails( @RequestParam String customerId ) {

		Integer id = null;
		if ( ! isJWTEnabled ) {
			// here the id is going to be the email address. so cast to a string,
			// get the customer -> get the id -> then get the account
			List<Customer> customers = customerRepository.findByEmailAddress( (String) customerId);
			
			if ( customers != null && ! customers.isEmpty() ) {
				id = customers.get(0).getCustomerId();
			}
			else { 
				return null;
			}
		}
		else {
			id = Integer.valueOf(customerId);
		}
		
		List<Loan> loans = loanRepository.findByCustomerIdOrderByStartDateDesc( id );
		
		return loans;
	}
}
