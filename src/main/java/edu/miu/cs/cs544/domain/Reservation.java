package edu.miu.cs.cs544.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private LocalDate reservationDate;
    @Enumerated
    private ReservationState state;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;
    @Embedded
    private AuditData auditData;

}
