package com.salesianostriana.dam.lakademialite.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.lakademialite.model.Clase;
import com.salesianostriana.dam.lakademialite.repository.IClaseRepository;
import com.salesianostriana.dam.lakademialite.service.base.BaseService;

@Service
public class ClaseServicio extends BaseService <Clase, Long, IClaseRepository>{

	public ClaseServicio(IClaseRepository repo) {
		super(repo);
		
	}

	public boolean comprobarPlazasDisponibles(Clase clase) {
		boolean hayEspacio = false;
		if(clase.getPlazas()>clase.getListaAlumnos().size())
			hayEspacio = true;
		return hayEspacio;
	}
	
	
}
