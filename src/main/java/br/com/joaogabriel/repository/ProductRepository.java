package br.com.joaogabriel.repository;

import java.util.UUID;

import br.com.joaogabriel.dao.impl.SimpleGenericDao;
import br.com.joaogabriel.studies.entity.Product;

public class ProductRepository extends SimpleGenericDao<Product, UUID>{

	public ProductRepository(Class<Product> type) {
		super(type);
	}
	

}
