package com.salesianostriana.dam.lakademialite.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlumnoUserDetailsServiceImpl implements UserDetailsService{
	
	private final AlumnoService alumnoService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return alumnoService.buscarPorEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}
}
