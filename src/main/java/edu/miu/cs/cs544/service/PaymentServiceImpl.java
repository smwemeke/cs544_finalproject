package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Payment;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.PaymentDto;
import edu.miu.cs.cs544.repository.PaymentRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void makePayment(PaymentDto paymentDto) {
         Payment payment = new Payment();
        Optional<Reservation> reservation = reservationRepository.findById(paymentDto.getReservationId());
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(paymentDto.getAmount());
        payment.setReservation(reservation.get());
        paymentRepository.save(payment);
        reservation.get().setState(ReservationState.Processed);
    }

    @Override
    public List<PaymentDto> getPaymentsForReservation(Integer reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
       return  paymentRepository.findPaymentsByReservation(reservation.get()).stream().map(r -> new PaymentDto().buildFromDomain(r)).toList();
        //return null;
    }
}
