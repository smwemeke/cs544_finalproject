package edu.miu.cs.cs544.integration.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReservationHandler {

    @Autowired
    OrderService service;

    @KafkaListener(topics = {"placeorderrequest"}, groupId = "orderhandler")
    public void receiveAccCreateRequest(@Payload String orderRequest) throws JsonProcessingException {

        System.out.println("Account Create Request: Receiver received message= "+ orderRequest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        PlaceOrderRequest dto = mapper.readValue(orderRequest, PlaceOrderRequest.class);
        service.placeOrder(dto);
        System.out.println("Order Placed, Email Confirm sent");
    }

}