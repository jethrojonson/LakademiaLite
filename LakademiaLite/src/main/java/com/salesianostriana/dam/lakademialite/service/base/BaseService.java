package com.salesianostriana.dam.lakademialite.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService <T, ID, R extends JpaRepository<T, ID>> implements IBaseService <T, ID> {
	
	@Autowired
	protected R repositorio;
	
	@Override
	public T save(T t) {
		return repositorio.save(t);
	}
	
	@Override
	public T findById(ID id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public List<T> findAll() {
		return repositorio.findAll();
	}
	
	@Override
	public T edit(T t) {
		return repositorio.save(t);
	}
	
	@Override
	public void delete(T t) {
		repositorio.delete(t);
	}
	
	@Override
	public void deleteById(ID id) {
		repositorio.deleteById(id);
	}
	
}
