package com.bcallanan.myBank.jpa;

import org.springframework.data.repository.CrudRepository;

import com.bcallanan.myBank.entity.MyBankRecord;

public interface BankRepository extends CrudRepository< MyBankRecord,Integer >{
	
}
