package com.salesianostriana.dam.lakademialite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping({"/","/index"})
	public String inicio() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registro")
	public String registrarse() {
		return "registro";
	}
}
