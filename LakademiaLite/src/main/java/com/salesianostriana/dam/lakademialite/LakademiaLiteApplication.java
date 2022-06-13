package com.salesianostriana.dam.lakademialite;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.service.AlumnoServicio;

@SpringBootApplication
public class LakademiaLiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(LakademiaLiteApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(AlumnoServicio alumnoServicio, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			
			Alumno a1 = new Alumno();
			a1.setNombre("Jerónimo Manuel");
			a1.setApellidos("Pérez González");
			a1.setDni("54104807-K");
			a1.setFechaNac(LocalDate.of(1993, 10, 24));
			a1.setEmail("perez.gojer22@triana.salesianos.edu");
			a1.setPassword(passwordEncoder.encode("1234"));
			a1.setAdmin(false);
			
			alumnoServicio.save(a1);
			
			
		};
	}

}
