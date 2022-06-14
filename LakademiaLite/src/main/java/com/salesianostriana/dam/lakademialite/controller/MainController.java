package com.salesianostriana.dam.lakademialite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.lakademialite.model.Admin;
import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.service.AlumnoServicio;

@Controller
public class MainController {
	
	@Autowired
	private AlumnoServicio alumnoServicio;
	
	@GetMapping({"/","/index"})
	public String inicio(@AuthenticationPrincipal Alumno alumno,@AuthenticationPrincipal Admin admin, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
			model.addAttribute("rol",alumno.getRol());
		}
		if(admin!=null) {
			model.addAttribute("nombre",admin.getUsername());
			model.addAttribute("rol",admin.getRol());
		}
		return "index";
	}
	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal Alumno alumno,@AuthenticationPrincipal Admin admin, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
			model.addAttribute("rol",alumno.getRol());
		}
		if(admin!=null) {
			model.addAttribute("nombre",admin.getUsername());
			model.addAttribute("rol",admin.getRol());
		}
		return "login";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}

	@GetMapping("/registro")
	public String registrarse(@AuthenticationPrincipal Alumno alumno,@AuthenticationPrincipal Admin admin, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
			model.addAttribute("rol",alumno.getRol());
		}
		if(admin!=null) {
			model.addAttribute("nombre",admin.getUsername());
			model.addAttribute("rol",admin.getRol());
		}
		
		model.addAttribute("alumno",new Alumno());
		
		return "registro";
	}
	
	@PostMapping("/registro/submit")
	public String nuevoRegistro(@ModelAttribute("alumno") Alumno nuevo) {
		alumnoServicio.save(nuevo);
		return "redirect:/login";
	}
}
