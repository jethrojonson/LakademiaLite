package com.salesianostriana.dam.lakademialite.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.repository.IAlumnoRepository;
import com.salesianostriana.dam.lakademialite.service.base.BaseService;

@Service
public class AlumnoServicio extends BaseService<Alumno, Long, IAlumnoRepository>{
	
	public Optional<Alumno> buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}
	
}
