package com.kodlamaio.inventoryService.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.kodlamaio.common.utilities.security.KeycloakJwtRoleConverter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtRoleConverter());
	    
		httpSecurity.authorizeHttpRequests()
		    .antMatchers("/api/brands","/api/cars","/api/models")
		    .hasAnyRole("admin","user","developer")
		    .antMatchers("\"/swagger-ui.html\", \"/swagger-ui/**\", \"/v3/api-docs/**\", \"/swagger-resources/**\", \"/webjars/**\"")
		    .permitAll()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .oauth2ResourceServer()
		    .jwt()
		    .jwtAuthenticationConverter(authenticationConverter);
		    
		    return httpSecurity.build();
		    
	}
}