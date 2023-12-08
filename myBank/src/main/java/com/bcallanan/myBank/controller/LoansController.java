package com.bcallanan.myBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Loan;
import com.bcallanan.myBank.jpa.LoanRepository;

@RestController
public class LoansController {

	@Autowired
	LoanRepository loanRepository;
	
	@GetMapping( value = { "/myLoans","/myloans" })
	public List<Loan> getLoanDetails( @RequestParam Integer customerId ) {
		
		return loanRepository.findByCustomerIdOrderByStartDateDesc(customerId);
	}
}
