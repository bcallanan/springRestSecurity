package com.bcallanan.myBank.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bcallanan.myBank.filter.CsrfCookieFilter;

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
			    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-requested-with", "x-xsrf-token" ));
				corsConfig.setAllowCredentials( true );
				corsConfig.setMaxAge( 3600L );
		
				return corsConfig;//new CorsFilter(urlSource);
			}
		};
		return corsConfigSource;
	}

	/**
	 * An convenience api to creating CsrfTokenRequestHandler interface that is capable of
	 * making the CsrfToken available as a request attribute and resolving the token
	 * value as either a header or parameter value of the request.
	 *
	 * @return
	 */
	CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler() {
		CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler =
				new CsrfTokenRequestAttributeHandler();
		csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName( "_csrf" );
		
		return csrfTokenRequestAttributeHandler;
	}
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		/**
		 *  Below is the custom security configurations
		 */
		http  // --- HttpSecurity
		/**
		 *	There are several points here to be made:
		 *   1) there will be no direct access to the endpoints
		 *   2) without the next 2 lines the user credentials would be needed on every request
		 *   	 .securityContext().requireExplicitSave(false) // this turns off the adhock JSession ID the default was 'true'
		 *      .and().sessionManagement((session) -> session.sessionCreationPolicy( SessionCreationPolicy.ALWAYS))
		 *   3) This tells the spring security framework to always create the JSession id by following the
		 *      session management create here and after the initial login is completed.
		 *      The JSession id is then sent to the Web Application to be used. The Web Application
		 *      can leverage the same JSession id for all subsequent requests.
		 */
	
		/**
		 *  This turns off the default login session the spring security provides
		 *  it also tells the spring security framework that it will not store the 
		 *  authentication details in the spring security contect holder. The code here
	     *  is doing the work.
	     */
			.securityContext().requireExplicitSave(false) // it turns off the adhock JSession ID the default was 'true'
			.and().sessionManagement((session) -> session.sessionCreationPolicy( SessionCreationPolicy.ALWAYS ))
			
			.cors().configurationSource( corsConfigurationSource() ) 
        	.and().csrf( (csrf) -> csrf.csrfTokenRequestHandler( csrfTokenRequestAttributeHandler())
        			.ignoringRequestMatchers( /*"/contact",*/ "/register") 
        			
        			/**
        			 * A {@link CsrfTokenRepository} that persists the CSRF token in a cookie named
        			 * "XSRF-TOKEN" and reads from the header "X-XSRF-TOKEN" following the conventions of
        			 * AngularJS. When using with AngularJS be sure to use {@link #withHttpOnlyFalse()}
        			 * Not only Angular but most ui frameworks like react etc. they follow the same
        			 * cookie format - cookie_name/header_name.
        			 */
        			.csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse()))
        	
        	/**
        	 *  We need to send the header and cookie value information everytime for that
        	 *  we need to create a filter class. This will create a filter that passes the
        	 *  token in each request -- The filter implements OncePerRequestFilter for 'every-request'
        	 */
        	.addFilterAfter( new CsrfCookieFilter(), BasicAuthenticationFilter.class)
        	.authorizeHttpRequests((requests) -> requests
        			// Commented out the number of endpoints in lieu of a call that says all(anyRequest)
        			// are authentication required.
					//.requestMatchers("/welcome", "/", "/myAccount","/myBalance","/myLoans","/myCards", "/user").authenticated()
				.requestMatchers("/notices",/*"/contact",*/ "/register").permitAll()//)
				.anyRequest().authenticated()) // little easier to wildcard the authentication
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
