package com.salesianostriana.dam.lakademialite.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NoArgsConstructor
@AllArgsConstructor
public class Alumno implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	private String apellidos;
	private String dni;
	private LocalDate fechaNac;
	
	/*USERNAME*/
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private boolean admin;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
		(
			name = "alumno_clases",
			joinColumns = @JoinColumn (name = "alumno_id"),
			inverseJoinColumns = @JoinColumn (name = "clase_id")
		)
	private List <Clase> listaClases = new ArrayList <Clase>();
	
	/*METODOS HELPERS*/
	
	public void addClase(Clase c) {
		listaClases.add(c);
		c.getListaAlumnos().add(this);
	}
	
	public void removeClase(Clase c) {
		listaClases.remove(c);
		c.getListaAlumnos().remove(this);
	}

	/*METODOS DE LA INTERFAZ UserDetails*/
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
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
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
	
}
