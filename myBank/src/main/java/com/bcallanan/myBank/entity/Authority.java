package com.bcallanan.myBank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table( name = "authorities" )
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int authorityId;
	
	@ManyToOne
	@JoinColumn( name = "customerId", nullable = false)
	private Customer customer;
	
	@Column( name = "authority_type_action")
	private String authorityTypeAction;
}
