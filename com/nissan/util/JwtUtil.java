package com.nissan.util;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nissan.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	//secret key for token
	private static String secretA="THIS_IS_ADMIN";
	private static String secretC="THIS_IS_CUSTOMER";
	
	//expiration time
	private static long expiryDuration=60*60;
	
	//generate token: header.payload.signature
	
	public String generateJwtAdmin(User user) {
		long milliTime=System.currentTimeMillis();
		long expiryTime=milliTime+expiryDuration*1000;
		
		//set issuedTime and ExpiryTime in token
		Date issuedAt=new Date(milliTime);
		Date expiryAt=new Date(expiryTime);
		
		//claims
		Claims claim=Jwts.claims().setIssuer(user.getUserId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);
		
		//generate JWT token using claims
		return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, secretA).compact();}
		
		//checking token is valid or not
		public Claims verifyA(String authorization) throws AccessDeniedException {
			try {
				Claims claim=Jwts.parser().setSigningKey(secretA).parseClaimsJws(authorization).getBody();
				return claim;
			}catch(Exception e) {
				throw new AccessDeniedException("Sorry! Access is denied");
			}
		}
		
		public String generateJwtCustomer(User user) {
			long milliTime=System.currentTimeMillis();
			long expiryTime=milliTime+expiryDuration*1000;
			
			//set issuedTime and ExpiryTime in token
			Date issuedAt=new Date(milliTime);
			Date expiryAt=new Date(expiryTime);
			
			//claims
			Claims claim=Jwts.claims().setIssuer(user.getUserId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);
			
			//generate JWT token using claims
			return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, secretC).compact();}
			
			//checking token is valid or not
			public Claims verifyC(String authorization) throws AccessDeniedException {
				try {
					Claims claim=Jwts.parser().setSigningKey(secretC).parseClaimsJws(authorization).getBody();
					return claim;
				}catch(Exception e) {
					throw new AccessDeniedException("Sorry! Access is denied");
				}
			}
		
		
	}


