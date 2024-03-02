package br.com.joaogabriel.dao.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.joaogabriel.dao.CrudDao;
import br.com.joaogabriel.studies.util.PersistenceUnit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public abstract class SimpleGenericDao<T, ID> implements CrudDao<T, ID> {
	
	private EntityManager entityManager = PersistenceUnit.getInstance().getEntityManager();
	private Class<T> domainClass;
	private Logger logger; 
	private char firstLetter; 
	private String entityName;
	
	protected SimpleGenericDao(Class<T> type) {
		this.domainClass = type;
		this.logger = Logger.getLogger(domainClass.getName());
		firstLetter = domainClass.getSimpleName().charAt(0);
		this.entityName = domainClass.getSimpleName();
	}

	@Override
	public T save(T type) {
		logger.log(Level.INFO, "Saving entity into database.");
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(type);
			this.entityManager.getTransaction().commit();
			return type;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unable to save object to database.");
			throw new SimpleGenericPersistenceException(e.getMessage());
		}
	}

	@Override
	public T update(T type) {
		try {
			this.entityManager.getTransaction().begin();
			boolean contains = this.entityManager.contains(type);
			if (contains) {
				this.entityManager.refresh(type);
				this.entityManager.getTransaction().commit();
				return type;
			} else {
				save(type);
				return type;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unable to update object into database.");
			throw new SimpleGenericPersistenceException(e.getMessage());
		}
	}

	@Override
	public void delete(ID id) {
		logger.log(Level.INFO, "Deleting entity by id {}", id);
		try {
			this.entityManager.getTransaction().begin();
			T entity = this.entityManager.find(domainClass, id);
			if (entity == null) return;
			this.entityManager.remove(entity);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unable to delete object from database.");
			throw new SimpleGenericPersistenceException(e.getMessage());
		}
		
	}

	@Override
	public Collection<T> findAll() {
		logger.log(Level.INFO, "Find all elements from database.");
		String search = String.format("Select %s from %s %s", firstLetter, entityName, firstLetter);
		Query query = this.entityManager.createQuery(search, domainClass);
		@SuppressWarnings("unchecked")
		Collection<T> list = query.getResultList();
		
		if (list == null) {
			return Collections.emptyList();
		}
		return list;
	}

	@Override
	public T findById(ID id) {
		logger.log(Level.INFO, "Finding entity by id {}", id);
		try {
			return this.entityManager.find(domainClass, id);			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unable to find object from database.");
			throw new SimpleGenericPersistenceException(e.getMessage());
		}
	}

}
