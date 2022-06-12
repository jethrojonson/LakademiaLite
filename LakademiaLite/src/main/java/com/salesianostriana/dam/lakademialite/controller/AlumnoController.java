package com.salesianostriana.dam.lakademialite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlumnoController {
	
	@GetMapping("/alumno/clases")
	public String mostrarClases() {
		return "clases";
	}
}
