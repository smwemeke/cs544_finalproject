package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	
	private String lastName;

	private String email;
	@Embedded
	private AuditData auditData;

	@ManyToOne
	private Address physicalAddress;

	@ManyToOne
	private Address billingAddress;

	@OneToOne(mappedBy = "customer")
	private User user;
}
