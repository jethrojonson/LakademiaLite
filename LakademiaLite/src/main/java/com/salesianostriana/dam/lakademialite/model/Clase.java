package com.salesianostriana.dam.lakademialite.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clase {
	
	@Id
	@GeneratedValue
	private Long claseId;
	
	private String tituloClase;
	private String precio;
	private String descripcion;
	private LocalDateTime fechaYHora;
	private int numeroPlazas;
	
	@ManyToMany(mappedBy="clases",fetch = FetchType.EAGER)
	private List <Alumno> alumnos = new ArrayList <> ();
}
