package com.salesianostriana.dam.lakademialite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.lakademialite.model.Alumno;

@Controller
public class AlumnoController {
	
	@GetMapping("/alumno/clases")
	public String mostrarClases(@AuthenticationPrincipal Alumno alumno, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
		}
		return "clases";
	}
}
