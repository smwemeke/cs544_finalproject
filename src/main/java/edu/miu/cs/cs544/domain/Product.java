package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name; 
	
	private String description;
	
	private String excerpt;

	private Double rate;
	private Integer maxCapacity;
	@Enumerated
	private ProductType type;
	@Embedded
	private AuditData auditData;

	private boolean isAvailable;
	public Product(){}

	public Product(String name, String description, String excerpt, Double rate, Integer maxCapacity, ProductType type) {
		this.name = name;
		this.description = description;
		this.excerpt = excerpt;
		this.rate = rate;
		this.maxCapacity = maxCapacity;
		this.type = type;
	}
}
