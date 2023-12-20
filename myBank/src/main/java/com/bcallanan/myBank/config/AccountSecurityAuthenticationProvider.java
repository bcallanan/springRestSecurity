package com.bcallanan.myBank.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bcallanan.myBank.entity.Authority;
import com.bcallanan.myBank.entity.Customer;
import com.bcallanan.myBank.jpa.CustomerRepository;

@Component
@ConditionalOnProperty(name="spring.security.config.jwt", havingValue = "true")
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
		String emailAddress = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		List<Customer> userAccounts = customerRepository.findByEmailAddress( emailAddress );
		if ( userAccounts.size() > 0 ) {
			if ( passwordEncoder.matches( password, userAccounts.get( 0 ).getPwd())) {
				// success
				return new UsernamePasswordAuthenticationToken( emailAddress, password,
						getAuthorities( userAccounts.get(0).getAuthorities()));
			}
			else {
				throw new BadCredentialsException( "Account Credentials are invalid! Password bad.");
			}
		}
		else {
			throw new BadCredentialsException( "Account Credentials are invalid! No user exists.");
		}
	}

	private List<GrantedAuthority> getAuthorities( List< Authority> authorities ) {
		
		List<GrantedAuthority> grantedAuthorites = new ArrayList<>();
		authorities.forEach( (authority) -> grantedAuthorites.
				add( new SimpleGrantedAuthority( authority.getAuthorityTypeAction()) ));
		
		return grantedAuthorites;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
