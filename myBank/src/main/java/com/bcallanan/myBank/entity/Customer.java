package com.bcallanan.myBank.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	private int customerId;
	
	private String name;

	@NotNull
	@NotBlank
	private String emailAddress;
	
	private String mobileNumber;
	
	@NotNull
	@NotBlank
	private String pwd;
	@NotNull
	@NotBlank
	private String role;
	
	private Date createDate;
	
	@Transient
	private String registrationToken; 
}
