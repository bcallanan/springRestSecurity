/**
 * 
 */
package com.bcallanan.myBank.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bcallanan.myBank.constants.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Done on every authentication except logon. 
 */
public class JWTTokenValidationFilter extends OncePerRequestFilter {

	/**
	 * @param request
	 * @param response 
	 * 
	 * That way its always present in the request/response header(s) 
	 */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    	// This filter will be just past the Basic Authentication filter. Therefore, it shouldn't be null 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (null != authentication) {
        	// key from the constants
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            
            // TODO: fixed deprecation issues
            String jwt = Jwts.builder().issuer("BC Bank").subject("BC JWT Token")
            		
            		// Username from the authentication object
                    .claim("username", authentication.getName())
                    // Authorities from the authenticated account
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + 1800000)) //<-- 30 minutes
                    .signWith(key).compact();
            
            // put the token in the the response back to the web app or other.
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
    	// this is for every other endpoint 'except' the login
        return request.getServletPath().equals("/user");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}