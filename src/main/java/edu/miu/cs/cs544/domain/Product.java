package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class Product  extends AuditableEntity  {
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

	public Product(int id,String name, String description, String excerpt, Double rate, Integer maxCapacity, ProductType type,  boolean isAvailable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.excerpt = excerpt;
		this.rate = rate;
		this.maxCapacity = maxCapacity;
		this.type = type;
		//	this.auditData = auditData;
		this.isAvailable = isAvailable;
	}
	public Product(){}

}
