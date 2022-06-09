package com.salesianostriana.dam.lakademialite.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
	
	@Id
	@GeneratedValue
	private Long alumnoId;
	
	private String nombreAlumno;
	private String apellidosAlumno;
	private LocalDate fechaNac;
	private String dni;
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			joinColumns = @JoinColumn(name="alumno_id"),
			inverseJoinColumns = @JoinColumn(name="clase_id")
			)
	private List <Clase> clases = new ArrayList <> (); 

/*METODOS HELPERS*/

	public int getEdad() {
		return (int) ChronoUnit.YEARS
				.between(fechaNac, 
						LocalDate.now()
						.with(TemporalAdjusters.lastDayOfYear()));
	}
	
	
	
	
	
	
}