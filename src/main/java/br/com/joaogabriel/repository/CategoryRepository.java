package br.com.joaogabriel.repository;

import java.util.UUID;

import br.com.joaogabriel.dao.impl.SimpleGenericDao;
import br.com.joaogabriel.studies.entity.Category;

public class CategoryRepository extends SimpleGenericDao<Category, UUID> {

	protected CategoryRepository(Class<Category> type) {
		super(type);
	}

}
