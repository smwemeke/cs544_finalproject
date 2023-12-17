package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
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

	public Product() {
	}
}
