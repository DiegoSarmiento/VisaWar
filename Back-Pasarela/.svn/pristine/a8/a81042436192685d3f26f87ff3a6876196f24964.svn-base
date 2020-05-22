package com.spring.visa.seguridad;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author EA
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String token;
	
	public JwtAuthenticationToken(String token) {
		super(null, null);
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	
}
