package br.com.joaogabriel.dao;

import java.util.Collection;

public interface CrudDao<T, ID> {
	
	T save(T type);
	
	T update(T update);
	
	void delete (ID id);
	
	Collection<T> findAll();
	
	T findById(ID id);

}
