package br.com.joaogabriel.studies.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;

public class PersistenceUnitTest {
	
	@Test
	@DisplayName("Given a valid persistence xml, when create entity manager factory, then return object of entity manager")
	public void givenAValidPersistence_whenCreateEntityManagerFactory_thenReturnEntityManager() {
		EntityManager entityManager = PersistenceUnit.getInstance().getEntityManager(); 
		Assertions.assertThat(entityManager).isNotNull();
		entityManager.close();
	}
}
