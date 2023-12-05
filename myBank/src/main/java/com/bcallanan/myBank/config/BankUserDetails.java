package com.bcallanan.myBank.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.CustomerRepository;

// @Service - turned off to use customized AuthenticationProvider
// @see AccountSecurityAuthenticationProvider
public class BankUserDetails implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {

		String password;
		
		List< GrantedAuthority> authorities;
		List< Customer > customers = customerRepository.findByEmailAddress( customerName );
		
		if ( customers.size() == 0 ) {
			throw new UsernameNotFoundException( "Customer with named account: " + customerName + " not found");
		}
		else {
			customerName = customers.get( 0 ).getEmailAddress(); 
			password = customers.get( 0 ).getPwd();
			authorities = new ArrayList<>();
			authorities.add( new SimpleGrantedAuthority( customers.get(0).getRole()));
		}
		
		// TODO Auto-generated method stub
		return new User(customerName, password, authorities);
	}

}
