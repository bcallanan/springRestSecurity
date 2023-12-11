/**
 * 
 */
package com.bcallanan.myBank.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bcallanan.myBank.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Done on every authentication except logon. 
 */
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

	/**
	 * @param request
	 * @param response 
	 * 
	 * That way its always present in the request/response header(s) 
	 * @throws java.io.IOException 
	 */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    	// This filter will be just past the Basic Authentication filter.
    	
    	// get the jwt token from the header
        String jwtTokenToValidate = request.getHeader( SecurityConstants.JWT_HEADER);
        
        if ( null != jwtTokenToValidate ) {
        	try {
	        	// key from the constants
	            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
	            
	            // TODO: this is deprecated and removed going forward
	            Claims claims = Jwts.parserBuilder()
	            		.setSigningKey(key)
	            		.build()
	            		.parseClaimsJws( jwtTokenToValidate)
	            		.getBody();
	            		
	            // Username from the token
	            String userName = String.valueOf( claims.get("username" ));
	            // authorities from the token
	            String authorities = String.valueOf( claims.get("authorities"));
	 
	            // do put in the password - we dont know it anyway so leave it null
	            Authentication auth = new UsernamePasswordAuthenticationToken( userName, null,
	            		AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
	            
	            SecurityContextHolder.getContext().setAuthentication( auth );
        	} catch( Exception e ) { //<-- catch all
        		e.printStackTrace();
        		throw new BadCredentialsException( "Invalid Token!");
        	}
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
    	// this is for every other endpoint 'except' the login
        return request.getServletPath().equals("/user");
    }
}
