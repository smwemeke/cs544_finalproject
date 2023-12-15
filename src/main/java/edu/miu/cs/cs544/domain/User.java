package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String userName;
	
	private String userPass;
	
	private Boolean active;
	@Enumerated
	private UserType type;
	@Embedded
	private AuditData auditData;

	@OneToOne
	private Customer customer;
	
}
