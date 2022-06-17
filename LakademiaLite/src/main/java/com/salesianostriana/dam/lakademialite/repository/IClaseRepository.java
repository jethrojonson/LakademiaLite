package com.salesianostriana.dam.lakademialite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.lakademialite.model.Clase;

public interface IClaseRepository extends JpaRepository <Clase, Long>{
	
	Optional<Clase> findByTituloClase(String tituloClase);
}
