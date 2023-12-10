/**
 * 
 */
package com.bcallanan.myBank.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
public class LoggingFilterAfterAuthorityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	    	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {
			// with this kind of logging here it will leave a trace of the login attempts
			// that can be sent via a logback impl for splunk or similar.
			log.info("User " + authentication.getName() + 
					" is successfully authenticated and has the authorities " +
					authentication.getAuthorities().toString());
		}

		chain.doFilter(request, response);
	}
}
