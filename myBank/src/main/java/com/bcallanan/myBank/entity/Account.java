package com.bcallanan.myBank.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table( name = "accounts" )
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int accountNumber;
	
	@JsonIgnore
	int customerId;

//	@Enumerated( EnumType.STRING )
	private AccountEnumType accountType;
	
	private String branchAddress;
	private Date createDate;
}
