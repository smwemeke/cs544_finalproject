package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class State  extends AuditableEntity {
	@Id
	@GeneratedValue
	private Integer id;

	private String code;
	
	private String name;


	@ManyToOne
	private Country country;
}
