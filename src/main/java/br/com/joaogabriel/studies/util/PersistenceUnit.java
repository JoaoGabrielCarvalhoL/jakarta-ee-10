package br.com.joaogabriel.studies.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.joaogabriel.studies.exception.PersistenceUnitException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceUnit {

	private EntityManagerFactory entityManagerFactory = null;
	private static PersistenceUnit instance = null;
	private final Logger logger = Logger.getLogger(PersistenceUnit.class.getName());
	
	private PersistenceUnit() {
		createEntityManagerFactory();
	}
	
	public static synchronized PersistenceUnit getInstance() {
		if (instance == null) {
			instance = new PersistenceUnit();
		}
		return PersistenceUnit.instance;
	}

	private EntityManagerFactory createEntityManagerFactory() {
		try {
			this.entityManagerFactory = Persistence.createEntityManagerFactory("studiesPersistenceUnit");
			return this.entityManagerFactory;
		} catch (Exception exception) {
			logger.log(Level.SEVERE, "Unable to create entity manager factory");
			throw new PersistenceUnitException(exception.getMessage());
		}
	}
	
	public EntityManager getEntityManager() {
		return this.entityManagerFactory.createEntityManager();
	}
}
