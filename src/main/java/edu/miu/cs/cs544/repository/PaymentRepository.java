package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Payment;
import edu.miu.cs.cs544.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository <Payment, Integer> {
    List<Payment> findPaymentsByReservation(Reservation reservation);
}
