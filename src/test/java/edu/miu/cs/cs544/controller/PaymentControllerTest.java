package edu.miu.cs.cs544.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs544.controller.PaymentController;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.PaymentDto;
import edu.miu.cs.cs544.service.PaymentService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc
public class PaymentControllerTest {
    @MockBean
    private PaymentService paymentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMakePayment() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(300);
        paymentDto.setReservationId(1);
        paymentDto.setAmount(200.0);
        paymentDto.setPaymentDate(LocalDate.now());

        mockMvc.perform(MockMvcRequestBuilders.post("/payment")
                        .content(asJsonString(paymentDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(paymentService, times(1)).makePayment(paymentDto);

    }
    @Test
    public void testGetPaymentsForReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setReservationId(1);
        paymentDto.setAmount(200.0);
        paymentDto.setPaymentDate(LocalDate.now());

        PaymentDto paymentDto1 = new PaymentDto();
        paymentDto1.setReservationId(1);
        paymentDto1.setAmount(300.0);
        paymentDto1.setPaymentDate(LocalDate.now());

        List<PaymentDto> paymentDtoList = new ArrayList();
        paymentDtoList.add(paymentDto1);
        paymentDtoList.add(paymentDto);

        Mockito.when(paymentService.getPaymentsForReservation(Mockito.anyInt())).thenReturn(paymentDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/{reservationId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(paymentDtoList)));
    }
    private String asJsonString(final Object paymentObj) {
        try {
            return new ObjectMapper().writeValueAsString(paymentObj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//                .andExpect(status().isOk())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.payment").isArray())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.payment", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$payment[0].."))
    }
