package com.salesianostriana.dam.lakademialite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.lakademialite.model.Alumno;

public interface IAlumnoRepository extends JpaRepository <Alumno, Long>{
	
	Optional<Alumno> findFirstByEmail(String email);

//	@Query("select")
	
}
