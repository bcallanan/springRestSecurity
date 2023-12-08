package com.bcallanan.myBank.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cardId;
	private int cardNumber;
	
//	@OneToOne
//	@JoinColumn( name = "customerId")
	private int customerId;

	@Enumerated( EnumType.STRING )
	private CardEnumType cardType;

	private int cardLimit;
	private int amountOutstanding;
	private int amountAvailable;
	private Date createDate;

}
