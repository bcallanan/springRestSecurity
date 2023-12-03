package com.bcallanan.myBank.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bcallanan.myBank.entity.Card;

@Repository
interface CardRepository extends CrudRepository< Card, Integer >{
	
	List< Card > findByCardName( String name ); 

}
