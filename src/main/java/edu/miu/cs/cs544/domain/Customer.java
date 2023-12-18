package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer  extends AuditableEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	
	private String lastName;

	private String email;


	@ManyToOne(cascade = CascadeType.ALL)
	private Address physicalAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address billingAddress;

	@OneToOne(mappedBy = "customer")
	private User user;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Address getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(Address physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
