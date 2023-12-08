package com.bcallanan.myBank.entity;

import java.util.Date;

import org.hibernate.annotations.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
//@org.hibernate.annotations.TypeDef(name = "enum_type", typeClass = PostgreSQLEnumType.class)
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int loanNumber;
	
//	@OneToOne
//	@JoinColumn( name = "customerId")
	private int customerId;

//	@Enumerated( EnumType.STRING )
//	@Enumerated(EnumType.STRING)
//	@Type(type = "enum_type")
	private LoanEnumType loanType;
	
	private int totalLoanValue;
	private int amountPaid;
	private int outstandingBalance;
	
	private Date startDate;
	private Date createDate;
}
