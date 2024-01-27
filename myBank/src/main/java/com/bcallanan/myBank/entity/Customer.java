package com.bcallanan.myBank.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This class defines the customer account where it is a foreign key
 * relationship to several classes. In this class you'll notice @Data 
 * it not ebing used as an annotation. The hash/toString is throwing 
 * an exception of some type. It'll need some additional debugging. I 
 * already spent 1/2 a day trying to figure out the annotation was 
 * part of the cause/symptom. The authorities were coming back empty 
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
//TODO - fix the annotation issue
//@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int customerId;
	
	private String name;

	@NotNull
	@NotBlank
    @Email(regexp=".*@.*\\..*", message = "Email should be valid - JaneDoe@email.com")
	private String emailAddress;
	
	private String mobileNumber;
	
	@NotNull
	@NotBlank
	@JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
	private String pwd;
	
	@NotNull
	@NotBlank
	private String role;
	
	private Date createDate;
	
	@Transient
	private String registrationToken;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer" )
	@Fetch( FetchMode.SELECT)
	private List< Authority > authorities;
}
