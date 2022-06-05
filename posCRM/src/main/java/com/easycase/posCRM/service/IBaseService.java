package com.easycase.posCRM.service;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T> {
	public T save(T obj);
	public List<T> findAll();
	public void delete(Long id);
	public void delete(T obj);
	public Optional<T> find(Long id);
	
}
