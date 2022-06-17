package com.salesianostriana.dam.lakademialite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.lakademialite.model.Admin;
import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.model.Clase;
import com.salesianostriana.dam.lakademialite.service.AlumnoServicio;
import com.salesianostriana.dam.lakademialite.service.ClaseServicio;

@Controller
public class AlumnoController {
	
	@Autowired
	private AlumnoServicio alumnoServicio;
	
	@Autowired
	private ClaseServicio claseServicio;
	
	@GetMapping("/alumno/clases")
	public String mostrarClases(@AuthenticationPrincipal Alumno alumno,@AuthenticationPrincipal Admin admin, Model model) {
		if(alumno!=null) {
			model.addAttribute("nombre",alumno.getNombre());
			model.addAttribute("rol",alumno.getRol());
			model.addAttribute("aluClases",alumnoServicio.findById(alumno.getId()).getListaClases());
			model.addAttribute("totalGastado",alumno.getTotalGastado());
			
		}
		if(admin!=null) {
			model.addAttribute("nombre",admin.getUsername());
			model.addAttribute("rol",admin.getRol());
		}
		model.addAttribute("clases",claseServicio.findAll());
		return "clases";
	}
	
	@GetMapping("/alumno/apuntarse/{id}")
	public String apuntarseAClase(@AuthenticationPrincipal Alumno alumno, @PathVariable("id") long id,Model model) {
		model.addAttribute("clases",claseServicio.findAll());
		model.addAttribute("id",id);
		Clase clase = claseServicio.findById(id);
		if(alumno!=null && claseServicio.comprobarPlazasDisponibles(clase)) {
			for(Clase c:alumnoServicio.findById(alumno.getId()).getListaClases()) {
				if(c.getId()==id)
					return "redirect:/alumno/clases";
			}
			alumno.getListaClases().clear();
			alumno.addClase(clase);
			alumnoServicio.edit(alumno);
		}
		return "redirect:/alumno/clases";
	}
	
	@GetMapping("/alumno/quitarClase/{id}")
	public String darseBajaClase (@PathVariable("id") long id, @AuthenticationPrincipal Alumno alumno) {
		Clase clase = claseServicio.findById(id);
		List <Clase> clasesAux = alumnoServicio.findById(alumno.getId()).getListaClases();
		clasesAux.remove(clase);
		alumno.setListaClases(clasesAux);
		alumno = alumnoServicio.descontarCancelacion(alumno, clase);
		alumnoServicio.edit(alumno);
		return "redirect:/alumno/clases";
	}
	
}

















