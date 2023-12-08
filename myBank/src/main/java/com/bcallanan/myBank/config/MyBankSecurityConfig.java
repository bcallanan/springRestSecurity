package com.bcallanan.myBank.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MyBankSecurityConfig {

	@Value("${spring.security.cors.domains}")
	public List<String> domains;
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfigurationSource corsConfigSource = new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration( HttpServletRequest request ) {
				
				CorsConfiguration corsConfig = new CorsConfiguration();
				corsConfig.setAllowedOrigins( domains);
				//this can be more specific as well.
				corsConfig.setAllowedMethods( Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE" ) );
				corsConfig.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
			    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-requested-with"));
				corsConfig.setAllowCredentials( true );
				corsConfig.setMaxAge( 3600L );
		
				return corsConfig;//new CorsFilter(urlSource);
			}
		};
		return corsConfigSource;
	}

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		/**
		 *  Below is the custom security configurations
		 */

		http//.cors().configurationSource( corsConfigurationSource() )
        	//.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
        	.authorizeHttpRequests((requests) -> requests
					//.requestMatchers("/welcome", "/", "/myAccount","/myBalance","/myLoans","/myCards", "/user").authenticated()
				.requestMatchers("/notices","/contact", "/register").permitAll()
				.anyRequest().authenticated()) // little easier to wildcard
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		return http.build();

		/**
		 *  Configuration to deny all the requests
		 */
		/*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
	                .formLogin(Customizer.withDefaults())
	                	.httpBasic(Customizer.withDefaults());
	        return http.build();*/

		/**
		 *  Configuration to permit all the requests
		 */
		/*http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
	                .formLogin(Customizer.withDefaults())
	                	.httpBasic(Customizer.withDefaults());
	        return http.build();*/
	}
	   
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		   
//		// approach1 default password encode is obviously not preferred
//		/* UserDetails adminDetails = User.withDefaultPasswordEncoder()
//					.username( "admin" )
//					.password( "pasword")
//					.authorities( "admin")
//					.build();
//		   
//		   UserDetails regularDetails = User.withDefaultPasswordEncoder()
//					.username( "fred" )
//					.password( "pasword")
//					.authorities( "read")
//					.build();
//			
//		   UserDetails brianDetails = User.withDefaultPasswordEncoder()
//					.username( "brian" )
//					.password( "pasword")
//					.authorities( "admin")
//					.build(); */
//		   
//		// approach1 default password encode is obviously not preferred
//		// default password encode is obviously not preferred
//		UserDetails adminDetails = User.withUsername( "admin" )
//				.password( "pasword" )
//				.authorities( "admin" )
//				.build();
//		   
//		UserDetails regularDetails = User.withUsername( "fred" )
//				.password( "pasword" )
//				.authorities( "read" )
//				.build();
//			
//		UserDetails brianDetails = User.withUsername( "brian" )
//				.password( "pasword" )
//				.authorities( "admin" )
//				.build();
//			
//		//return new JdbcUserDetailsManager( adminDetails, regularDetails, brianDetails);
//		return new InMemoryUserDetailsManager( adminDetails, regularDetails, brianDetails);
//	}
//	   
	
	/**
	 * 
	 * @param dataSource
	 * @return
	 */
	//@Bean
	//public JdbcUserDetailsManager userDetailsService(DataSource dataSource ) {
	public UserDetailsService userDetailsService(DataSource dataSource ) {
		return new JdbcUserDetailsManager( dataSource );
	}
	
	/**
	 * this is plaintext password approach
	 * @return
	 */
	//@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		//return NoOpPasswordEncoder.getInstance();
	}
}
