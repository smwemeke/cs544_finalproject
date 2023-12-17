package edu.miu.cs.cs544.dto.orders;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class PlaceOrderRequest {

    private Integer customerId;
    private LocalDate reservationDate;

    private List<CreateItemRequest> items;
}
