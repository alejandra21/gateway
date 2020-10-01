package com.example.gatewayservice.controllers;

import java.util.Arrays;
import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController {

	
	@PostMapping("/auth")
	public String getAuth() {
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject("pepe")
			.claim("user", "pepe")
			.claim("authorities", Arrays.asList("USER"))
			.setIssuedAt(new Date(now))
			.signWith(SignatureAlgorithm.HS512, "UkXp2s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F)J@NcRfUjWnZr4u7x!A%D*G-KaP".getBytes())
			.compact();
		
		
		return token;
	}
}
