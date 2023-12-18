package edu.miu.cs.cs544.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class Reservation  extends AuditableEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        String itemIds  = getItems().stream().map(x->x.getProduct().getId().toString()).collect(Collectors.joining(" "));
        return Objects.equals(getCustomer().getId(), that.getCustomer().getId()) && Objects.equals(getReservationDate(), that.getReservationDate()) && Objects.equals(itemIds, that.getItems().stream().map(x->x.getProduct().getId().toString()).collect(Collectors.joining(" ")));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getReservationDate(), getItems());
    }
}
