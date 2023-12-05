package com.bcallanan.myBank.entity;

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
	@NotNull
	@NotBlank
	private String emailAddress;
	@NotNull
	@NotBlank
	private String pwd;
	@NotNull
	@NotBlank
	private String role;
	
	@Transient
	private String registrationToken; 
}
