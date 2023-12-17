package edu.miu.cs.cs544.dto.orders;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
@Data
@Validated
public class PlaceOrderRequest {

    private Integer customerId;
    @FutureOrPresent
    private LocalDate reservationDate;

    private List<CreateItemRequest> items;
}
