package com.bcallanan.myBank.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	//@GenericGenerator( name = "native", type = org.hibernate.id..class)
	private int customerId;
	private String emailAddress;
	private String pwd;
	private String role;
}
