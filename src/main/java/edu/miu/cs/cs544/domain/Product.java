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
@NoArgsConstructor
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

	private boolean isAvailable;
	
}
