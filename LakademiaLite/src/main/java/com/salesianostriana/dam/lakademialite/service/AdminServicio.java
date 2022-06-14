package com.salesianostriana.dam.lakademialite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.lakademialite.model.Admin;
import com.salesianostriana.dam.lakademialite.repository.IAdminRepository;
import com.salesianostriana.dam.lakademialite.service.base.BaseService;

@Service
public class AdminServicio extends BaseService<Admin, Long, IAdminRepository>{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AdminServicio(IAdminRepository repo) {
		super(repo);
	}
	
	public Optional <Admin> buscarPorNombre (String username){
		return repositorio.findFirstByUsername(username);
	}
	
	@Override
	public Admin save(Admin t) {
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		return super.save(t);
	}
	
	
}
