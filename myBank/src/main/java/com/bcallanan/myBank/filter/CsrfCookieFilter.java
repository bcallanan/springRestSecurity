/**
 * 
 */
package com.bcallanan.myBank.filter;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Populating the token.
 * 
 * Spring security will also generate the cookie as well as part of the response
 * 
 */
public class CsrfCookieFilter extends OncePerRequestFilter {

	/**
	 * @param request - has the csrfToken in the request.
	 * @param response - set the csrf token in the header 
	 * 
	 * That way its always present in the request/response header(s) 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		CsrfToken csrfToken = ( CsrfToken ) request.getAttribute( CsrfToken.class.getName());
		if ( null != csrfToken.getHeaderName()) {
			
			response.setHeader( csrfToken.getHeaderName(), csrfToken.getToken());
		}
		
		filterChain.doFilter(request, response);
	}
}
