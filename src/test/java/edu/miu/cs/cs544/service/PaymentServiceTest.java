package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Payment;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.PaymentDto;
import edu.miu.cs.cs544.repository.PaymentRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.PaymentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class PaymentServiceTest {
    @MockBean
    private PaymentRepository paymentRepository;
    @MockBean
    private ReservationRepository reservationRepository;
    @Autowired
    private PaymentService paymentService;

    @Test
    public void testMakePayment(){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setReservationId(1);
        paymentDto.setAmount(200.0);

        Reservation reservation = new Reservation();
        reservation.setState(ReservationState.Processed);

        Mockito.when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
        Mockito.when(paymentRepository.save(any())).thenReturn(new Payment());

        paymentService.makePayment(paymentDto);

        Mockito.verify(paymentRepository, Mockito.times(1)).save(any());
        Mockito.verify(reservationRepository, Mockito.times(1)).findById(1);
        assert (reservation.getState().equals(ReservationState.Processed));
    }
    @Test
    public void testGetPaymentForReservations(){
        int reservationId = 1;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        reservation.setState(ReservationState.Processed);

        Payment payment = new Payment();
        payment.setAmount(150.0);
        payment.setReservation(reservation);

        Mockito.when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        Mockito.when(paymentRepository.findPaymentsByReservation(reservation)).thenReturn(List.of(payment));

        List<PaymentDto> result = paymentService.getPaymentsForReservation(reservationId);

        // Assertions
        Mockito.verify(reservationRepository, Mockito.times(1)).findById(reservationId);
        Mockito.verify(paymentRepository, Mockito.times(1)).findPaymentsByReservation(reservation);
        assert (reservation.getState().equals(ReservationState.Processed));

    }
}