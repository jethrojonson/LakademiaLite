package com.salesianostriana.dam.lakademialite;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.salesianostriana.dam.lakademialite.model.Admin;
import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.service.AdminServicio;
import com.salesianostriana.dam.lakademialite.service.AlumnoServicio;

@SpringBootApplication
public class LakademiaLiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(LakademiaLiteApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(AlumnoServicio alumnoServicio) {
		return args -> {
			
			Alumno alumno = new Alumno();
			alumno.setNombre("user");
			alumno.setEmail("user");
			alumno.setPassword("1234");
			
			alumnoServicio.save(alumno);
			
			Alumno a1 = new Alumno();
			a1.setNombre("Jeronimo M.");
			a1.setApellidos("Pérez González");
			a1.setDni("54104807-K");
			a1.setFechaNac(LocalDate.of(1993, 10, 24));
			a1.setEmail("perez.gojer22@triana.salesianos.edu");
			a1.setPassword("1234");
			
			alumnoServicio.save(a1);
			
			
		};
	}
	
	@Bean
	public CommandLineRunner init1(AdminServicio adminServicio) {
		return args -> {
			
			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setPassword("admin");
			
			adminServicio.save(admin);
			
			Admin ad1 = new Admin();
			ad1.setUsername("Luismi");
			ad1.setPassword("1234");
			
			adminServicio.save(ad1);
			
		};
	}

}
