package edu.miu.cs.cs544.integration.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.integration.eventlistener.SendEmailEvent;
import edu.miu.cs.cs544.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReservationHandler {

    @Autowired
    OrderService service;
@Autowired
private ApplicationEventPublisher publisher;
    @KafkaListener(topics = {"placeorderrequest"}, groupId = "orderhandler")
    public void receiveAccCreateRequest(@Payload String orderRequest) throws JsonProcessingException {

        System.out.println("Account Create Request: Receiver received message= "+ orderRequest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        PlaceOrderRequest dto = mapper.readValue(orderRequest, PlaceOrderRequest.class);
        var order = service.placeOrder(dto);
        System.out.println("Order Placed, Email Confirm sent");
        publisher.publishEvent(new SendEmailEvent("Customer@email.com","Order Placed request is Confirm:" + order.getId().toString()));
    }

}