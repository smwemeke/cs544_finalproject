package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer occupants;
	
	private LocalDate checkinDate;

	private LocalDate checkoutDate;

	private Double rate;

	private Integer unit;

	@ManyToOne
	@JoinColumn(name="orderId")
	private Reservation order;

	@ManyToOne
	private Product product;

	@Embedded
	private AuditData auditData;
	
}
