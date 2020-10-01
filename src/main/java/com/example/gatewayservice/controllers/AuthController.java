package com.example.gatewayservice.controllers;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController {

	@Value("${security.jwt.secret}")
    private String secretKey;
	
	@PostMapping("/auth")
	public String getAuth() {
		
		log.info(secretKey);
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject("USER")
			.claim("user", "USER")
			.claim("authorities", Arrays.asList("USER"))
			.setIssuedAt(new Date(now))
			.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
			.compact();
		
		
		return token;
	}
}
