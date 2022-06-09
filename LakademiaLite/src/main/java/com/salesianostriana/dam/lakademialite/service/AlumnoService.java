package com.salesianostriana.dam.lakademialite.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.repository.AlumnoRepository;
import com.salesianostriana.dam.lakademialite.service.base.BaseService;

@Service
public class AlumnoService extends BaseService<Alumno,Long,AlumnoRepository>{
	
	public AlumnoService(AlumnoRepository repo) {
		super(repo);
	}
	
	public Optional<Alumno> buscarPorEmail(String email){
		return repositorio.findFirstByEmail(email);
	}
}
