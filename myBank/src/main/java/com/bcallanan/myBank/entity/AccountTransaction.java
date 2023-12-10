package com.bcallanan.myBank.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table( name = "account_transactions" )
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@JsonIgnore
	private int transactionId;
	
	private int accountNumber;
	
	@JsonIgnore
	private int customerId;
	
	private Date transactionDate;
	private String transactionSummary;

//	@Enumerated( EnumType.STRING )
	private TransactionEnumType transactionType;
	
	private int transactionAmount;
	private int closingBalance;
	private Date createDate;
}
