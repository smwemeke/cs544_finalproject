package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Payment;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.repository.PaymentRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class PaymentRepositoryTest {

   @MockBean
    private PaymentRepository paymentRepository;
   @MockBean
    ReservationRepository reservationRepository;
   @Test
    public void testFindPaymentByReservation(){
       Reservation reservation = new Reservation();
       reservation.setId(1);


       Payment payment1 = new Payment();
       payment1.setReservation(reservation);
       payment1.setAmount(200.0);
       payment1.setPaymentDate(LocalDate.now());

       Payment payment2 = new Payment();
       payment2.setReservation(reservation);
       payment2.setAmount(300.0);
       payment2.setPaymentDate(LocalDate.now());

       reservationRepository.save(reservation);
       paymentRepository.save(payment1);
       paymentRepository.save(payment2);

       List<Payment> payment = new ArrayList<>();
       payment.add(payment1);
       payment.add(payment2);

       List<Payment> resultPayments = paymentRepository.findPaymentsByReservation(reservation);

       Mockito.when(resultPayments).thenReturn(payment);

       assertEquals(payment.size(), 2);
   }
}