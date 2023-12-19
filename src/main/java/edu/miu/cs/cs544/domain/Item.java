package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.cs.cs544.dto.orders.CreateItemRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item  extends AuditableEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer occupants;

    private LocalDate checkinDate;

    private LocalDate checkoutDate;

    private Double rate;

    private Integer unit;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Reservation order;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Product product;



    public Item buildFromDto(CreateItemRequest request){
        occupants = request.getOccupants();
        rate = request.getRate();
        unit = request.getUnit();
        return this;
    }

}
