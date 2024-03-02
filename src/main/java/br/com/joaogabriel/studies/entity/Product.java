package br.com.joaogabriel.studies.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Table(name = "tb_product")
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, length = 50, unique = true)
	private String name;
	
	@Column(nullable = false)
	private Integer amount;
	
	@Column(nullable = false, scale = 2, precision = 10)
	private BigDecimal price;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private LocalDate expirationDate;
	
	private Boolean isPerishable;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Category category;
	
	public Product() { }
	
	public Product(String name, Integer amount, BigDecimal price, LocalDate expirationDate, Boolean isPerishable) {
		this.name = name; 
		this.amount = amount; 
		this.price = price; 
		this.expirationDate = expirationDate;
		this.isPerishable = isPerishable;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public Boolean getIsPerishable() {
		return isPerishable;
	}

	public void setIsPerishable(Boolean isPerishable) {
		this.isPerishable = isPerishable;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
}
