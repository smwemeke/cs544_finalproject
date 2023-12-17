package edu.miu.cs.cs544.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.integration.jms.JMSSender;
import edu.miu.cs.cs544.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@Validated
public class ReservationsController {
    @Autowired
    OrderService orderService;

    @Autowired
    JMSSender sender;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Integer orderId) {

        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }
//    @GetMapping("/available/{productId}")
//    public ResponseEntity<?> checkProductAvailable(@PathVariable Integer productId){
//        return new ResponseEntity<>(customerService.isAvailable(productId), HttpStatus.OK) ;
//    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        //TODO Check product available
        var isAvailable = orderService.isAvailable(request.getReservationDate(), request.getItems());
        if (!isAvailable) {
            return new ResponseEntity<>("Product is not available in the reservation date: " + request.getReservationDate(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.placeOrder(request), HttpStatus.OK);
    }

    @PostMapping("/asyncs")
    public ResponseEntity<?> placeOrderAsync(@RequestBody PlaceOrderRequest request) throws JsonProcessingException {
        //put to queue
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String requestString = objectMapper.writeValueAsString(request);
        sender.send("placeorderrequest", requestString);
        return new ResponseEntity<>("Order is Successfully placed, you will receive confirmation within 24 hrs", HttpStatus.ACCEPTED);
    }

//    @PostMapping("/items/{itemId}/checkins")
//    public ResponseEntity<?> checkIn(@PathVariable int itemId,@RequestBody StateChangeRequest request){
//        //TODO validate customer validat for this item
//
//        return new ResponseEntity<>(customerService.checkIn(request), HttpStatus.OK) ;
//    }
//
//    @PostMapping("/orders/items/{itemId}/checkouts")
//    public ResponseEntity<?> checkOut(@PathVariable int itemId,@RequestBody StateChangeRequest request){
//        //TODO validate customer validat for this item
//
//        return new ResponseEntity<>(customerService.checkOut(request), HttpStatus.OK) ;
//    }

//    @PostMapping("/orders/items/{itemId}/cancels")
//    public ResponseEntity<?> cancel(@PathVariable int itemId,@RequestBody StateChangeRequest request){
//        //TODO validate customer validat for this item
//
//        return new ResponseEntity<>(customerService.cancel(request), HttpStatus.OK) ;
//    }
}
