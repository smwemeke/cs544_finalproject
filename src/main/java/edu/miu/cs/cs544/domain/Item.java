package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import edu.miu.cs.cs544.dto.orders.CreateItemRequest;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
    private Reservation order;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;



    public Item buildFromDto(CreateItemRequest request){
        occupants = request.getOccupants();
        rate = request.getRate();
        unit = request.getUnit();
        return this;
    }

}
