package com.salesianostriana.dam.lakademialite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.repository.IAlumnoRepository;
import com.salesianostriana.dam.lakademialite.service.base.BaseService;

@Service
public class AlumnoServicio extends BaseService<Alumno, Long, IAlumnoRepository>{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AlumnoServicio(IAlumnoRepository repo) {
		super(repo);
	}
	
	public Optional<Alumno> buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}

	@Override
	public Alumno save(Alumno t) {
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		return super.save(t);
	}
	
	
	
	
}
