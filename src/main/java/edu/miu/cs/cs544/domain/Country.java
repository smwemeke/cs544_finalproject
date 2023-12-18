package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Country extends AuditableEntity  {
	
	@Id
	private String code;
	
	private String name;
	
	private Integer population;

	
}
