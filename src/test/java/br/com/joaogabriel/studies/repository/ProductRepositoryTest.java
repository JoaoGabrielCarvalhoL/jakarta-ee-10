package br.com.joaogabriel.studies.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.joaogabriel.repository.ProductRepository;
import br.com.joaogabriel.studies.entity.Category;
import br.com.joaogabriel.studies.entity.Product;
import br.com.joaogabriel.studies.entity.builder.ProductBuilder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryTest {
	
	private ProductRepository productRepository;
	private Product product;
	
	@BeforeAll
	public void setup() {
		this.productRepository = new ProductRepository(Product.class);
		this.product = new ProductBuilder().
				name("Product Test A")
				.amount(15).
				price(new BigDecimal(100.00))
				.expirationDate(LocalDate.of(2024, 06, 1))
				.isPerishable(false)
				.category(new Category("Category Test A"))
				.build();
	}
	
	@Test
	@DisplayName("Given product object, when save object, then return product persisted from database.")
	@Order(2)
	public void givenProductObject_whenSave_thenReturnProductPersisted() {
		Product saved = this.productRepository.save(product);
		Assertions.assertThat(saved).isNotNull();
		Assertions.assertThat(saved.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Given valid id, when find product by id, then return product persisted")
	@Order(3)
	public void givenValidId_whenFindProductById_thenReturnProductPersisted() {
		Product saved = this.productRepository.save(product);
		UUID id = saved.getId();
		Product result = this.productRepository.findById(id);
		Assertions.assertThat(saved.getId()).isEqualTo(result.getId());
		Assertions.assertThat(saved).isEqualTo(result);
	}
	
	@Test
	@DisplayName("Given invalid id, when find product by id, then return null")
	@Order(4)
	public void givenInvalidId_whenFindProductById_thenReturnNull() {
		UUID id = UUID.randomUUID();
		Product result = this.productRepository.findById(id);
		Assertions.assertThat(result).isNull();
	}
	
	@Test
	@DisplayName("Given product list, when find all, then return list of products from database")
	@Order(5)
	public void givenProductList_whenFindAll_thenReturnListOfProductsFromDatabase() {
		this.productRepository.save(product);
		Collection<Product> list = this.productRepository.findAll();
		Assertions.assertThat(list).isNotEmpty().hasSize(1);
	}
	
	@Test
	@DisplayName("Given product list, when find all, then return empty list")
	@Order(1)
	public void givenProductList_whenFindAll_thenReturnEmptyList() {
		Collection<Product> list = this.productRepository.findAll();
		Assertions.assertThat(list).isEmpty();
	}
	
	@Test
	@DisplayName("Given valid id, when delete product, then nothing")
	@Order(6)
	public void givenValidId_whenDeleteProduct_thenNothing() {
		UUID id = this.productRepository.save(product).getId();
		this.productRepository.delete(id);
	}

}
