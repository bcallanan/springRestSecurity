package com.bcallanan.myBank.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.CustomerRepository;

@Component
class AccountSecurityAuthenticationProvider implements AuthenticationProvider {
	/**
	 * @see UsernamePasswordAuthenticationToken.class
	 * @see DaoAuthenticationProvider.class
	 * @see AbstractUserDetailsAuthenticationProvider.class
	 * @see ProviderManager.class
	 */

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		/**
		 * define all authentication logic:
		 * 1) Load user details
		 * 2) comparing passwords
		 * 3) create authentication object - successful or not(failed)
		 * 4)
		 */
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		List<Customer> userAccounts = customerRepository.findByEmailAddress(username);
		if ( userAccounts.size() > 0 ) {
			if ( passwordEncoder.matches( password, userAccounts.get( 0 ).getPwd())) {
				// success
				List< GrantedAuthority> authorities = new ArrayList<>();
				authorities.add( new SimpleGrantedAuthority( userAccounts.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken( username, password, authorities);
			}
			else {
				throw new BadCredentialsException( "Account Credentials are invalid! Password bad.");
			}
		}
		else {
			throw new BadCredentialsException( "Account Credentials are invalid! No user exists.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
