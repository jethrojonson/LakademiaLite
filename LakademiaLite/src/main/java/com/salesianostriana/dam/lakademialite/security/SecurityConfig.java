package com.salesianostriana.dam.lakademialite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.salesianostriana.dam.lakademialite.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
//		auth
//		.inMemoryAuthentication()
//		.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
//		.withUser("admin")
//		.password("{noop}admin")
//		.roles("ADMIN");
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
				.and()
			.logout()
				.logoutUrl("/logout")
				.permitAll();
		
		/*PARA VER LA CONSOLA H2*/
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	
}
