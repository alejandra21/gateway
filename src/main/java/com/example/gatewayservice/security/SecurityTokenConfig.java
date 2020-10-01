package com.example.gatewayservice.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.jwt.uri}")
    private String jwtUri;
	
	@Value("${security.jwt.header}")
    private String jwtHeader;

	@Value("${security.jwt.prefix}")
    private String jwtPrefix;

	@Value("${security.jwt.secret}")
    private String secretKey;
	
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
    	   http
		.csrf().disable()
	 	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
		.and()
		    .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 	
		.and()
		.addFilterAfter(new JwtTokenAuthenticationFilter(jwtHeader,jwtPrefix,secretKey), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		   .antMatchers(
				   "/auth/**",
				   "/error",
                   "/v2-docs",
                   "/itinerary-api/v2/api-docs",
					"/v2/api-docs",
                   "/swagger-ui.html",
                   "/webjars/**",
                   "/swagger-resources/configuration/ui", 
                   "/swagger-resources/configuration/security",
                   "/swagger-resources/**").permitAll()
		   .anyRequest().authenticated(); 
	}

}