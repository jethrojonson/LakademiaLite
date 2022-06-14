package com.salesianostriana.dam.lakademialite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.lakademialite.model.Admin;

public interface IAdminRepository extends JpaRepository<Admin,Long>{
	
	Optional <Admin> findFirstByUsername(String username);
}
