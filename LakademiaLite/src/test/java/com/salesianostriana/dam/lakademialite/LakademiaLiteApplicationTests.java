package com.salesianostriana.dam.lakademialite;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.repository.IAlumnoRepository;

@SpringBootTest
class LakademiaLiteApplicationTests {

	@Autowired
	private IAlumnoRepository repo;
	
	@Test
	public void crearUsuarioTest() {
		
		Alumno alumno = new Alumno();
		
		alumno.setNombre("Antonio");
		alumno.setApellidos("Jiménez Infante");
		alumno.setDni("12345678-J");
		alumno.setFechaNac(LocalDate.of(1997, 01, 10));
		alumno.setEmail("antonio@email.es");
		alumno.setPassword("1234");
		alumno.setAdmin(false);
		
		Alumno retorno= repo.save(alumno);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(alumno.getPassword()));
		
	}
	

}
