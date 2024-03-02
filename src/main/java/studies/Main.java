package studies;

import br.com.joaogabriel.studies.util.PersistenceUnit;
import jakarta.persistence.EntityManager;

public class Main {

	public static void main(String[] args) {
		EntityManager entityManager = PersistenceUnit.getInstance().getEntityManager();
		if (entityManager.isOpen()) {
			System.out.println("'-'");
			entityManager.close();
		}
	}
}
