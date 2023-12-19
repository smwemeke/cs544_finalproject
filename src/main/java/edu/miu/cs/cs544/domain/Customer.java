package edu.miu.cs.cs544.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	
	private String lastName;

	private String email;
	@Embedded
	private AuditData auditData;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address physicalAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address billingAddress;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private User user;
	public Customer(String firstName, String lastName, String email, AuditData auditData, Address physicalAddress, Address billingAddress, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.auditData = auditData;
		this.physicalAddress = physicalAddress;
		this.billingAddress = billingAddress;
		this.user = user;
	}
}
