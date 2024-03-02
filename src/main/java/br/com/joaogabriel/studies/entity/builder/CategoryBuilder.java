package br.com.joaogabriel.studies.entity.builder;

import java.util.List;
import java.util.UUID;

import br.com.joaogabriel.studies.entity.Category;
import br.com.joaogabriel.studies.entity.Product;

public class CategoryBuilder {

	private Category category = new Category();
	
	public CategoryBuilder builder() {
		return new CategoryBuilder();
	}
	
	public CategoryBuilder id(UUID id) {
		this.category.setId(id);
		return this;
	}
	
	public CategoryBuilder name(String name) {
		this.category.setName(name);
		return this;
	}
	
	public CategoryBuilder products(List<Product> products) {
		this.category.setProducts(products);
		return this;
	}
	
	public Category build() {
		return this.category;
	}
}
