package com.salesianostriana.dam.lakademialite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.service.AlumnoService;

@SpringBootApplication
public class LakademiaLiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(LakademiaLiteApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(AlumnoService servicio, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			
			Alumno a = new Alumno();
			a.setAdmin(false);
			a.setNombreAlumno("Jerónimo M.");
			a.setApellidosAlumno("Pérez González");
			a.setEmail("jero_perez@email.com");
			a.setPassword(passwordEncoder.encode("1234"));
			
			servicio.save(a);
			
			
			Alumno b = new Alumno();
			b.setAdmin(true);
			b.setNombreAlumno("Luismi");
			a.setApellidosAlumno("Lopez");
			a.setEmail("luismi.lopez@email.com");
			a.setPassword(passwordEncoder.encode("1234"));
			
			servicio.save(b);
			
		};
	}

}
