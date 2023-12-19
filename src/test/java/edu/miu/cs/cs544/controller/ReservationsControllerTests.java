package edu.miu.cs.cs544.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs544.dto.orders.CreateItemRequest;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.integration.jms.JMSSender;
import edu.miu.cs.cs544.repository.OrderRepository;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.assertj.AssertableApplicationContext.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    CustomerService customerService;

    @MockBean
    JMSSender sender;

    @MockBean
    OrderRepository orderRepository;

    @Test
    public void createReservation_Test_success() throws Exception {
        var date = LocalDate.now().plusDays(2);
        List<CreateItemRequest> items = new ArrayList<>();
        Mockito.when(orderService.isAvailable(date, items)).thenReturn(true);
        var request = new PlaceOrderRequest();
        request.setReservationDate(LocalDate.now().plusDays(2));
        request.setCustomerId(1);
        request.setItems(items);
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        var contentJson = mapper.writeValueAsString( request);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentJson))

                .andExpect(status().isOk());

        //verify(orderService, times(1)).createAccount(acc.getAccountNumber(),acc.getBalance(),acc.getAccountHolder());

    }
}
