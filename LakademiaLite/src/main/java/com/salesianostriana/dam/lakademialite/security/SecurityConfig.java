package com.salesianostriana.dam.lakademialite.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*Método que configura la autenticacion*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/users/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
			.permitAll();
		
		// Añadimos esto para poder seguir accediendo a la consola de H2
		// con Spring Security habilitado.
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
}
