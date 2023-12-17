package edu.miu.cs.cs544.dto.orders;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateOrderRequest {
    @FutureOrPresent
    private LocalDate reservationDate;

    private Integer orderId;
}
