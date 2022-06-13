package com.salesianostriana.dam.lakademialite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.lakademialite.model.Alumno;

public interface IAlumnoRepository extends JpaRepository <Alumno, Long>{
	
	Alumno findByEmail(String email);

}
