package com.salesianostriana.dam.lakademialite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.lakademialite.model.Alumno;

public interface IAlumnoRepository extends JpaRepository <Alumno, Long>{
	
	public List<Alumno> findByNombreContainsIgnoreCase(String nombre);
	
	Optional<Alumno> findFirstByEmail(String email);

}
