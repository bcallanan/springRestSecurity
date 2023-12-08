package com.bcallanan.myBank.entity;

import java.util.Date;

import jakarta.persistence.Entity;
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
@Table( name = "contact_messages" )
public class Contact {

	@Id
	private String contactId;
	private String contactName;
	private String contactEmail;
	private String subject;
	private String message;
	private Date createDate;
}
