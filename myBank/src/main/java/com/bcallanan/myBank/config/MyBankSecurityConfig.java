package com.bcallanan.myBank.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bcallanan.myBank.filter.CsrfCookieFilter;
import com.bcallanan.myBank.filter.CustomRequestFilterBefore;
import com.bcallanan.myBank.filter.JWTTokenGenerationFilter;
import com.bcallanan.myBank.filter.JWTTokenValidationFilter;
import com.bcallanan.myBank.filter.JWTTokenValidatorFilter;
import com.bcallanan.myBank.filter.LoggingFilterAfterAuthorityFilter;

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
				corsConfig.setAllowedOrigins( domains );
				//this can be more specific as well.
				corsConfig.setAllowedMethods( Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE" ) );
				//update 5 -- customized header for JWT
				corsConfig.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
			    corsConfig.setAllowedHeaders( Collections.singletonList("*"));//Arrays.asList("Authorization", "content-type", "x-requested-with", "x-xsrf-token" ));
			               //authorization, content-type, strict-transport-security, x-frame-options

				corsConfig.setAllowCredentials( true );
				corsConfig.setMaxAge( 3600L );
		
				return corsConfig;
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
	
	/**
	 * An convenience api to creating JwtAuthenticationConverter that is capable of
	 * making the Jwt Granted Authorities Converter from the Kaycloak Converter
	 *
	 * @return
	 */
	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter jwtAuthenticationConverter =
				new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter( new KeycloakRoleConverter());
		
		return jwtAuthenticationConverter;
	}
	
    @Bean
	@ConditionalOnProperty(name="spring.security.config.oauth", havingValue = "true")
	SecurityFilterChain defaultOAuthSecurityFilterChain(HttpSecurity http) throws Exception {
		/**
		 *  Below is the custom security configurations
		 */
		log.info( "OAUTH Security Enabled");
		http  // --- HttpSecurity
			
		   //JWT Stateless Token Management: Update 5
           .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		     .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		     .csrf((csrf) -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler())
		     .ignoringRequestMatchers("/register")
        			
		     /**
		      * A {@link CsrfTokenRepository} that persists the CSRF token in a cookie named
		      * "XSRF-TOKEN" and reads from the header "X-XSRF-TOKEN" following the conventions of
		      * AngularJS. When using with AngularJS be sure to use {@link #withHttpOnlyFalse()}
		      * Not only Angular but most ui frameworks like react etc. they follow the same
		      * cookie format - cookie_name/header_name.
		      */
		     .csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse()))
        	
		// After validation of basic auth
		.addFilterAfter( new CsrfCookieFilter(), BasicAuthenticationFilter.class)

		// log requests of authentication
		.addFilterAfter( new LoggingFilterAfterAuthorityFilter(), BasicAuthenticationFilter.class)
		.authorizeHttpRequests((requests) -> requests
				
				// authenticated endpoints 
				.requestMatchers( "/contact", "/user", "/welcome", "/").authenticated()
				.requestMatchers( "/myAccount" ).hasRole( "USER")
				.requestMatchers( "/myBalance" ).hasAnyRole( "USER", "ADMIN")
				.requestMatchers( "/myLoans").authenticated()
				.requestMatchers( "/myCards").hasRole( "USER")
				.requestMatchers( "/notices", "/register").permitAll())
		.oauth2ResourceServer( (oauth2) -> oauth2.jwt(jwtConverter -> jwtConverter.
				jwtAuthenticationConverter( jwtAuthenticationConverter())));
		
		return http.build();
	}
	
	@Bean
	@ConditionalOnProperty(name="spring.security.config.jwt", havingValue = "true")
	SecurityFilterChain defaultJWTSecurityFilterChain(HttpSecurity http) throws Exception {

		/**
		 *  Below is the custom security configurations
		 */
		log.info( "JWT Security Enabled");
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
		
		//
		//		/**
		//		 *  This turns off the default login session the spring security provides
		//		 *  it also tells the spring security framework that it will not store the 
		//		 *  authentication details in the spring security contect holder. The code here
		//		 *  is doing the work.
		//		 */
		//		.securityContext().requireExplicitSave(false) // it turns off the adhock JSession ID the default was 'true'
		//		.and().sessionManagement((session) -> session.sessionCreationPolicy( SessionCreationPolicy.ALWAYS ))
		   //JWT Stateless Token Management: Update 5
           .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		     .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		     .csrf((csrf) -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler())
		     .ignoringRequestMatchers("/register")
        			
				/**
				 * A {@link CsrfTokenRepository} that persists the CSRF token in a cookie named
				 * "XSRF-TOKEN" and reads from the header "X-XSRF-TOKEN" following the conventions of
				 * AngularJS. When using with AngularJS be sure to use {@link #withHttpOnlyFalse()}
				 * Not only Angular but most ui frameworks like react etc. they follow the same
				 * cookie format - cookie_name/header_name.
				 */
				.csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse()))
        	
        /**
         *  When integrating the JWT Token into the headers we should validate the 
         *  Token before the authentication filter. But only for requests that are not
         *  the initial login. see interval implementation
         *  
         *  We need to send the header and cookie value information every-time. For that
         *  we need to create a filter class. This will create a filter that passes the
         *  token in each request -- The filter implements OncePerRequestFilter for 'every-request'.
         *  
         *  The JWT Token filter(JWTTokenGenerationFilter) is a OncePerRequestFilter as well. So, we
         *  only get one JSON Web token per authentication request, however, we override the filter
         *  behavior within the filter creation to *only* perform the filter during a 'logon'
         *  operation. So, we only get one JWT Token for the entire session length. When another
         *  login attempt occurs the filter will be executed again.
         *  
         *  The next JWT Token filter(JWTTokenValidationFilter) is also a OncePerRequestFilter
         *  where the token validation is done once except when it is *not* a 'logon' operation.
         */
		
		// before basicAuth for not for login see interval implementation
		.addFilterBefore( new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)

		// After validation of basic auth
		.addFilterAfter( new CsrfCookieFilter(), BasicAuthenticationFilter.class)

		// this filter will be executed once during login see interval implementation
		.addFilterAfter( new JWTTokenGenerationFilter(), BasicAuthenticationFilter.class)

		// this filter will be executed once except login see interval implementation
		.addFilterAfter( new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)

		.addFilterBefore( new CustomRequestFilterBefore(), BasicAuthenticationFilter.class)

		// log requests of authentication
		.addFilterAfter( new LoggingFilterAfterAuthorityFilter(), BasicAuthenticationFilter.class)
		.authorizeHttpRequests((requests) -> requests

				// authenticated endpoints - no roles or authorities
				.requestMatchers( "/contact", "/user", "/welcome", "/").authenticated()
				
				//authority based matchers	
					// .requestMatchers( "/myAccount" ).hasAuthority( "VIEWACCOUNT")
					// .requestMatchers( "/myBalance" ).hasAnyAuthority( "VIEWACCOUNT", "VIEWBALANCE")
					// .requestMatchers( "/myLoans").hasAuthority( "VIEWLOANS")
					// .requestMatchers( "/myCards").hasAuthority( "VIEWCARDS")
				
				/**
				 * role based matches -- the role that should be required which is
				 * prepended with ROLE_ automatically (i.e. USER, ADMIN, etc). It
				 *  should not start with ROLE_ The database should have the prefix though
				 */
				.requestMatchers( "/myAccount" ).hasRole( "USER")
				.requestMatchers( "/myBalance" ).hasAnyRole( "USER", "ADMIN")
				.requestMatchers( "/myLoans").authenticated()//.hasRole( "USER")
				.requestMatchers( "/myCards").hasRole( "USER")

				.requestMatchers( "/notices", "/register").permitAll())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	/**
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(name="spring.security.config.jwt", havingValue = "true")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
