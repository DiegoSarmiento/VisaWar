package com.spring.visa.seguridad;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author EA
 */
@Component
public class JwtUtil {
	
	//private final long EXPIRATION_TIME = 21_600_000; // 6 hours
	//private final long EXPIRATION_TIME = 1800000; // 30 minutos
	//private final long EXPIRATION_TIME = 60000; // 1 minuto
	  
	private final String SECRET = "VISA.2020";
	

	/**
	 * Parse Token
	 * 
	 * @param token, Token
	 * @return JwtUser
	 */
    public JwtUser parseToken(String token) {
    	JwtUser jwtUser = null;
    	
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            
            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        } catch (JwtException | ClassCastException e) {

        }
        
        return jwtUser;
    }

    /**
     * Generate Token
     * 
     * @param jwtUser, JwtUser
     * @return String
     */
    public String generateToken(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
        		.setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Constantes.EXPIRATION_TIME + 10*1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    /**
     * Generate Token
     * 
     * @param jwtUser, JwtUser
     * @return String
     */
    public String doExpireToken(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
        		.setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    /**
     * Generate Token Swagger
     * 
     * @param jwtUser, JwtUser 
     * @return String
     */
    public String generateTokenSwagger(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
        		.setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
}
