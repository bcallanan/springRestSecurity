package com.bcallanan.myBank.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bcallanan.myBank.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository< Account, Integer > {
	
	Account findByCustomerId( Integer customerId ); 
}
