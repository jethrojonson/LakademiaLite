package com.salesianostriana.dam.lakademialite.service;

import com.salesianostriana.dam.lakademialite.model.Admin;
import com.salesianostriana.dam.lakademialite.repository.IAdminRepository;
import com.salesianostriana.dam.lakademialite.service.base.BaseService;

public class AdminServicio extends BaseService<Admin, Long, IAdminRepository>{

	public AdminServicio(IAdminRepository repo) {
		super(repo);
	}
	
	
	
}
