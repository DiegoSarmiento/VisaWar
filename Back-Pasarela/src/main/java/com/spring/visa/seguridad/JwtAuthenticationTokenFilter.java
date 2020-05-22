package com.spring.visa.seguridad;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException; 

/**
 * @author EA
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	public JwtAuthenticationTokenFilter() {
		super("/visanetToken/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		
		logger.debug("inicio - attemptAuthentication");
		
		String header = httpServletRequest.getParameter("Authorization");
		 
			if (header == null ) {
				throw new JwtTokenMissingException("No JWT token found in request headers");
			}

		JwtAuthenticationToken authRequest;

	 	authRequest = new JwtAuthenticationToken(header);
		 
		
		logger.debug("fin - attemptAuthentication");

		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		logger.debug("successfulAuthentication");
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
}
