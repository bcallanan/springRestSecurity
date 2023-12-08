package com.bcallanan.myBank.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bcallanan.myBank.entity.Account;
import com.bcallanan.myBank.entity.AccountTransaction;
import com.bcallanan.myBank.entity.Customer;

@Repository
public interface AccountTransactionRepository extends CrudRepository< AccountTransaction, Integer > {
	
	List<AccountTransaction> findByCustomerIdOrderByTransactionDateDesc( Integer customerId ); 
}
