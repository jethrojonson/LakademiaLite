package com.salesianostriana.dam.lakademialite.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.lakademialite.model.Alumno;
import com.salesianostriana.dam.lakademialite.model.Clase;
import com.salesianostriana.dam.lakademialite.repository.IAlumnoRepository;
import com.salesianostriana.dam.lakademialite.service.base.BaseService;



@Service
public class AlumnoServicio extends BaseService<Alumno, Long, IAlumnoRepository>{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AlumnoServicio(IAlumnoRepository repo) {
		super(repo);
	}
	
	public Optional<Alumno> buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}

	@Override
	public Alumno save(Alumno t) {
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		return super.save(t);
	}
	
	public double totalInvertido(List <Alumno> lista) {
		double total = 0;
		for(Alumno a : lista) {
			total+=a.getTotalGastado();
		}
		return total;
	}
	
	public Alumno descontarCancelacion (Alumno alumno, Clase claseCancelada) {
		double descontadoCancel = 0;
		if(LocalDateTime.of(claseCancelada.getFecha(), claseCancelada.getHora()).isAfter(LocalDateTime.now())) {
			descontadoCancel = alumno.getTotalGastado() - claseCancelada.getPrecio();
			alumno.setTotalGastado(descontadoCancel);
		}
		return alumno;
	}
	
}
