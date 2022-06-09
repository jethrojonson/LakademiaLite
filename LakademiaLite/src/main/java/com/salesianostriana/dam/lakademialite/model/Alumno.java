package com.salesianostriana.dam.lakademialite.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno implements UserDetails{
	
	private static final long serialVersionUID =  1409538586158223652L;
	
	@Id
	@GeneratedValue
	private Long alumnoId;
	
	private String nombreAlumno;
	private String apellidosAlumno;
	private LocalDate fechaNac;
	private String dni;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private boolean admin;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			joinColumns = @JoinColumn(name="alumno_id"),
			inverseJoinColumns = @JoinColumn(name="clase_id")
			)
	private List <Clase> clases = new ArrayList <> (); 
/*METODOS INTERFAZ UserDetails*/
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		String role = "ROLE_";
		if (admin) {
			role += "ADMIN";
		} else {
			role += "USER";
		}
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
/*METODOS HELPERS*/

	public int getEdad() {
		return (int) ChronoUnit.YEARS
				.between(fechaNac, 
						LocalDate.now()
						.with(TemporalAdjusters.lastDayOfYear()));
	}
	
	public void addClase(Clase c) {
		clases.add(c);
		c.getAlumnos().add(this);
	}
	
	public void removeClase(Clase c) {
		clases.remove(c);
		c.getAlumnos().remove(this);
	}
	
	
	
	
	
	
}