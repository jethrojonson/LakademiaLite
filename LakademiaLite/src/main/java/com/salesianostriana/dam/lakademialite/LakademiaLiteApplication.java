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
			
			Alumno angel = new Alumno();
			angel.setNombre("Angel");
			angel.setApellidos("Naranjo");
			angel.setDni("76530912-J");
			angel.setFechaNac(LocalDate.of(1979,02,12));
			angel.setEmail("Angel@profe.com");
			angel.setPassword("1234");
			
			alumnoServicio.save(angel);
			
			
			Alumno a1 = new Alumno();
			a1.setNombre("Jeronimo M.");
			a1.setApellidos("Pérez González");
			a1.setDni("54104807-K");
			a1.setFechaNac(LocalDate.of(1993, 10, 24));
			a1.setEmail("perez.gojer22@triana.salesianos.edu");
			a1.setPassword("1234");
			
			alumnoServicio.save(a1);
			
			Alumno a2 = new Alumno();
			a2.setNombre("Rogelio");
			a2.setApellidos("Mohigefer");
			a2.setDni("58931025-H");
			a2.setFechaNac(LocalDate.of(1989, 06, 9));
			a2.setEmail("roge-music@nuevoberlin.es");
			a2.setPassword("hola");
			
			alumnoServicio.save(a2);
			
			Alumno a3 = new Alumno();
			a3.setNombre("Antonio");
			a3.setApellidos("Jiménez Infante");
			a3.setDni("14368529-W");
			a3.setFechaNac(LocalDate.of(1998, 9, 21));
			a3.setEmail("ska8@mail.com");
			a3.setPassword("peta");
			
			alumnoServicio.save(a3);
			
			Alumno a4 = new Alumno();
			a4.setNombre("Jaime");
			a4.setApellidos("Cárdenas");
			a4.setDni("11123558-D");
			a4.setFechaNac(LocalDate.of(1994, 11, 25));
			a4.setEmail("pro-gramer@hacker.es");
			a4.setPassword("hack");
			
			alumnoServicio.save(a4);
			
			
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
