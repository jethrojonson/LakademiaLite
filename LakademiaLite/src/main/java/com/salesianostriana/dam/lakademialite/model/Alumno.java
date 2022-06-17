package com.salesianostriana.dam.lakademialite.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno implements UserDetails{
	
	private static final long serialVersionUID = 1746882337033536373L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellidos;
	private String dni;
	private double totalGastado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNac;
	
	/*USERNAME*/
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@ManyToMany
		(
				fetch = FetchType.EAGER,
				cascade = {CascadeType.PERSIST,CascadeType.MERGE}
		)
	@JoinTable
		(
			name = "alumno_clases",
			joinColumns = @JoinColumn (name = "alumno_id"),
			inverseJoinColumns = @JoinColumn (name = "clase_id")
		)
	private List <Clase> listaClases = new ArrayList <>();
	
	/*METODOS HELPERS*/
	
	public void addClase(Clase c) {
		listaClases.add(c);
		this.totalGastado+=c.getPrecio();
		c.getListaAlumnos().add(this);
	}
	
	public void removeClase(Clase c) {
		listaClases.remove(c);
		c.getListaAlumnos().remove(this);
	}

	/*METODOS DE LA INTERFAZ UserDetails*/
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ALUMNO"));
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
	
	/*METODOS EXTRA*/
	
	public String getRol() {
		return "ALUMNO";
	}
	
	public long getEdad() {
		long edad;
		edad = ChronoUnit.YEARS.between(fechaNac, LocalDate.now());
		return edad;
	}
	
	
	
}
