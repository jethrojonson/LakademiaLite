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
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.inMemoryAuthentication()
			.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
			.withUser("admin")
			.password("{noop}admin")
			.roles("ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http
			.authorizeRequests()
				.antMatchers("/","/index","/registro").permitAll()
				.antMatchers("/alumno/**").hasAnyRole("ADMIN","ALUMNO")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and();
		
		/*PARA VER LA CONSOLA H2*/
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	
}
