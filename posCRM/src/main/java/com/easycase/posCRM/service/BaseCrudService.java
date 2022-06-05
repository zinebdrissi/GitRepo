package com.easycase.posCRM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public class BaseCrudService<T, R extends JpaRepository<T, Long>> implements IBaseService<T> {

	private R repository;

	public BaseCrudService(R repository) {
		this.repository = repository;

	}
	public T save(T obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	public void delete(Long id) {
		repository.deleteById(id);

	}
	public void delete(T obj) {
		repository.delete(obj);

	}
	public Optional<T> find(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

}
