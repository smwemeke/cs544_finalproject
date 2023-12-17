package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Payment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDto {
    private Integer id;

    private Integer reservationId;
    private double amount;
    private LocalDate paymentDate;

    public PaymentDto buildFromDomain(Payment payment){
        id = payment.getId();
        reservationId = payment.getReservation().getId();
        amount = payment.getAmount();
        paymentDate = payment.getPaymentDate();
        return this;
    }
}
