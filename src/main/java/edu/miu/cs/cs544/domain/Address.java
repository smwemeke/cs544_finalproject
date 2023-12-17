package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address extends AuditableEntity {
	@Id
	@GeneratedValue
	private Integer id;

	private String line1;
	
	private String line2;
	
	private String city;
	
	private String postalCode;
	@Enumerated
	private AddressType type;


	@ManyToOne
	private State state;

	public Address() {
	}

	public Address(String line1, String line2, String city, String postalCode, AddressType type, State state) {
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.postalCode = postalCode;
		this.type = type;
		this.state = state;
	}
}
