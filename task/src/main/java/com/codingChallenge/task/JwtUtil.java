package com.codingChallenge.task;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
private String SECRET_KEY = "secret";
	
	// this method is used to extract username from token that will come from ui
	// this happens automatically with spring security http basic
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
        
	}
	
	// this method will set the expiry date of token (usually it is 3 days) 3*24*60*60
     
	public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        } 
        
        // this methos resolves the claim placed by ui developer while giving the token
        // this claim must also be registered with overall Claims class
        
        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token); //extract the Claims basis given token
            return claimsResolver.apply(claims);
        }
        
        // this method first encodes the secret key use base64 encoder and parses it to create a strong token
        // such method are sometimes called as helper method 
        
        private Claims extractAllClaims(String token) {
            return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())).parseClaimsJws(token).getBody();
        }
        private String createToken(Map<String, Object> claims, String subject) {
        	 // this is called as builder design pattern which we are using jwts object
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()*3*24*60*60*1000))
                     .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                    .compact();
        }
               
        public String generateToken(String username) {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, username);
        }
        
        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }
        
        public Boolean validateToken(String token, String username) {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        }
	
}
