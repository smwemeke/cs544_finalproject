package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Payment;
import edu.miu.cs.cs544.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    void makePayment(PaymentDto paymentDto);
    public List<PaymentDto> getPaymentsForReservation(Integer reservationId);
}
