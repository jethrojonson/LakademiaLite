package com.salesianostriana.dam.lakademialite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.lakademialite.model.Admin;
import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.model.Clase;
import com.salesianostriana.dam.lakademialite.service.AdminServicio;
import com.salesianostriana.dam.lakademialite.service.AlumnoServicio;
import com.salesianostriana.dam.lakademialite.service.ClaseServicio;

@Controller
public class AdminController {
	
	@Autowired
	private AlumnoServicio alumnoServicio;
	
	@Autowired
	private AdminServicio adminServicio;
	
	@Autowired
	private ClaseServicio claseServicio;
	
	/*NUEVA CLASE*/
	
	@GetMapping("/admin/nuevaClase")
	public String formularioNuevaClase(@AuthenticationPrincipal Admin admin, Model model) {
		model.addAttribute("nombre",admin.getUsername());
		model.addAttribute("rol",admin.getRol());
		model.addAttribute("clase", new Clase());
		return "editar";
	}
	
	@PostMapping("/admin/nuevaClase/submit")
	public String confirmarNuevaClase(@ModelAttribute("clase") Clase nueva) {
		claseServicio.save(nueva);
		return "redirect:/alumno/clases";
	}
	
	/*EDITAR CLASE*/
	
	@GetMapping("/admin/editarClase/{id}")
	public String formularioEditarClase(@PathVariable("id")long id, @AuthenticationPrincipal Admin admin, Model model) {
		model.addAttribute("nombre",admin.getUsername());
		model.addAttribute("rol",admin.getRol());
		Clase clase = claseServicio.findById(id);
		model.addAttribute("clase", clase);
		return "editar";
	}
	
	@PostMapping("/admin/editarClase/sumbmit")
	public String confirmarEditarClase(@ModelAttribute("clase") Clase claseEdit) {
		claseServicio.edit(claseEdit);
		return "redirect:/alumno/clases";
	}
	
	/*BORRAR CLASE*/
	
	@GetMapping("/admin/borrarClase/{id}")
	public String borrarClase(@PathVariable("id")long id) {
		Clase clase = claseServicio.findById(id);
		for(Alumno a : clase.getListaAlumnos()) {
			a.getListaClases().remove(clase);
			a=alumnoServicio.descontarCancelacion(a, clase);
			alumnoServicio.edit(a);
		}
		claseServicio.delete(clase);
		return "redirect:/alumno/clases";
	}
	
	/*VER ALUMNOS EN CLASE*/
	
	@GetMapping("/admin/verAlumnos/{id}")
	public String verAlumnos(@PathVariable("id") long id, @AuthenticationPrincipal Admin admin, Model model) {
		Clase clase = claseServicio.findById(id);
		model.addAttribute("nombre",admin.getUsername());
		model.addAttribute("rol",admin.getRol());
		model.addAttribute("claseSelect",clase);
		model.addAttribute("alumnosEnLaClase",clase.getListaAlumnos());
		model.addAttribute("hayClase", true);
		model.addAttribute("clases",claseServicio.findAll());
		return "clases";
	}
	
	/*GESTION ALUMNOS*/
	
	@GetMapping("/admin/gestion")
	public String gestionar(@AuthenticationPrincipal Admin admin, Model model) {
		model.addAttribute("nombre",admin.getUsername());
		model.addAttribute("rol",admin.getRol());
		model.addAttribute("adminsRegistrados", adminServicio.findAll());
		model.addAttribute("alumnosRegistrados", alumnoServicio.findAll());
		model.addAttribute("total",alumnoServicio.totalInvertido(alumnoServicio.findAll()));
		return "gestion";
	}
	
	
	@GetMapping("/admin/borrarAlumno/{id}")
	public String borrarAlumno(@PathVariable("id") long id, @AuthenticationPrincipal Admin admin, Model model) {
		model.addAttribute("nombre",admin.getUsername());
		model.addAttribute("rol",admin.getRol());
		Alumno aluBorrado = alumnoServicio.findById(id);
		alumnoServicio.delete(aluBorrado);
		return "redirect:/admin/gestion";
	}
	
}
