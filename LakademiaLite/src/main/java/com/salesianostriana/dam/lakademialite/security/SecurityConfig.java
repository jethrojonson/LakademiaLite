package com.salesianostriana.dam.lakademialite.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
				.inMemoryAuthentication()
				.passwordEncoder(PasswordEncoderFactories
								.createDelegatingPasswordEncoder())
				.withUser("admin")
				.password("{noop}admin")
				.roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
	}
	
}
