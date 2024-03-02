package br.com.joaogabriel.studies.entity.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import br.com.joaogabriel.studies.entity.Category;
import br.com.joaogabriel.studies.entity.Product;

public class ProductBuilder {

	private Product product = new Product();
	
	public ProductBuilder id(UUID id) {
		this.product.setId(id);
		return this;
	}
	
	public ProductBuilder name(String name) {
		this.product.setName(name);
		return this;
	}
	
	public ProductBuilder amount(Integer amount) {
		this.product.setAmount(amount);
		return this;
	}
	
	public ProductBuilder price (BigDecimal price) {
		this.product.setPrice(price);
		return this;
	}
	
	public ProductBuilder expirationDate(LocalDate expirationDate) {
		this.product.setExpirationDate(expirationDate);
		return this;
	}
	
	public ProductBuilder category(Category category) {
		this.product.setCategory(category);
		return this;
	}
	
	public ProductBuilder isPerishable(Boolean isPerishable) {
		this.product.setIsPerishable(isPerishable);
		return this;
	}
	
	public Product build() {
		return this.product;
	}
}
