package com.salesianostriana.dam.lakademialite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.lakademialite.model.Alumno;

@Controller
public class MainController {
	
	@GetMapping({"/","/index"})
	public String inicio(@AuthenticationPrincipal Alumno alumno, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
		}
		return "index";
	}
	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal Alumno alumno, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
		}
		return "login";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}

	@GetMapping("/registro")
	public String registrarse(@AuthenticationPrincipal Alumno alumno, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
		}
		return "registro";
	}
}
