package com.bcallanan.myBank.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bcallanan.myBank.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository< Customer, Integer > {
	
	List<Customer> findByEmailAddress( String emailAddress ); 
}
