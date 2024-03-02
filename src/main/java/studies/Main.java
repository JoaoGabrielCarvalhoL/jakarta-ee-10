package studies;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("studiesPersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		if(entityManager.isOpen()) {
			System.out.println("Deu certo");
		}
	}
}
