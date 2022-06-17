package com.salesianostriana.dam.lakademialite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

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
	private long id;
	
	private String tituloClase;
	private String descripcion;
	private String imgUrl;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private LocalTime hora;
	private double precio;
	private int plazas;
	
	@ManyToMany(mappedBy = "listaClases", fetch = FetchType.EAGER)
	private List <Alumno> listaAlumnos = new ArrayList<>();

	/*METODOS AUX*/
	public int obtenerPlazasOcupadas() {
		return listaAlumnos.size();
	}
}
