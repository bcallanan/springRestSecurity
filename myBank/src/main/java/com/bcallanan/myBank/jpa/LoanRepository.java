package com.bcallanan.myBank.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.bcallanan.myBank.entity.Loan;

@Repository
public interface LoanRepository extends CrudRepository< Loan, Integer > {
	
	@PreAuthorize( "hasRole('USER')" )
	List<Loan> findByCustomerIdOrderByStartDateDesc( Integer customerId ); 
}
