package com.bcallanan.myBank.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bcallanan.myBank.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository< Contact, String > {
}
