package com.salesianostriana.dam.lakademialite.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.lakademialite.model.Admin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final AlumnoServicio alumnoServicio;
	private final AdminServicio adminServicio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<Admin> admin = adminServicio.buscarPorNombre(username); 
		
		if (admin.isPresent()) {
			return admin.get();
		}
		
		return alumnoServicio.buscarPorEmail(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
		
		
		
		
		/*return adminServicio.buscarPorNombre(username)
				.orElseThrow(() -> new UsernameNotFoundException("Admin no encotrado"));*/
		
//		return alumnoServicio.buscarPorEmail(username)
//				.orElseThrow(()-> new UsernameNotFoundException("Alumno no encontrado"));
		
//		if(alumnoServicio.buscarPorEmail(username)!=null) {
//			return alumnoServicio.buscarPorEmail(username)
//				.orElseThrow(()-> new UsernameNotFoundException("Alumno no encontrado"));
//		}
//		
//		return adminServicio.buscarPorNombre(username)
//				.orElseThrow(() -> new UsernameNotFoundException("Admin no encotrado"));
		
	}
	
	
	
}
