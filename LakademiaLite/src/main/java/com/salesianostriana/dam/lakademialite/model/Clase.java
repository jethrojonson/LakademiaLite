package com.salesianostriana.dam.lakademialite.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String tituloClase;
	private String descripcion;
	private LocalDateTime FechaYHora;
	private double precio;
	private int plazas;
	
	@ManyToMany(mappedBy = "listaClases", fetch = FetchType.EAGER)
	private List <Alumno> listaAlumnos = new ArrayList<Alumno>();

}
