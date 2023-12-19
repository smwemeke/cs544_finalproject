package edu.miu.cs.cs544.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.dto.orders.UpdateOrderRequest;
import edu.miu.cs.cs544.dto.orders.StateChangeRequest;
import edu.miu.cs.cs544.integration.jms.JMSSender;
import edu.miu.cs.cs544.repository.OrderRepository;
import edu.miu.cs.cs544.service.AdminService;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.OrderService;
import jakarta.transaction.Transactional;
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
    CustomerService customerService;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JMSSender sender;
    @Autowired
    AdminService adminService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Integer orderId) {

        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }
    @PutMapping("/{orderId}")
    public ResponseEntity<?> checkProductAvailable(@PathVariable Integer orderId, @RequestBody UpdateOrderRequest request){

        return new ResponseEntity<>(orderService.updateOrder(request), HttpStatus.OK) ;
    }

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

    @PostMapping("/orders/items/{itemId}/checkins")
    public ResponseEntity<?> CheckIn(@PathVariable int itemId,@RequestBody StateChangeRequest request){
        int customerId = request.getCustomerId();
        if (!adminService.existsById(customerId)){
            return  new ResponseEntity<>("Customer with ID" + customerId + "does not exist",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(adminService.checkIn(request), HttpStatus.OK) ;
    }
    @PostMapping("/orders/items/{itemId}/checkouts")
    public ResponseEntity<?> checkOut(@PathVariable int itemId,@RequestBody StateChangeRequest request){
         int customerId = request.getCustomerId();
         if(!adminService.existsById(customerId)){
             return new ResponseEntity<>("Customer with ID" + customerId + "does not exist",HttpStatus.BAD_REQUEST);
         }
        return new ResponseEntity<>(adminService.checkOut(request), HttpStatus.OK) ;
    }
        @PostMapping("/orders/items/{itemId}/cancels")
    public ResponseEntity<?> cancel(@PathVariable int itemId,@RequestBody StateChangeRequest request){
        int customerId = request.getCustomerId();
        if(!customerService.customerExistsById(customerId)){
            return new ResponseEntity<>("Customer with ID" + customerId + "does not exist",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerService.cancel(request), HttpStatus.OK) ;
    }
}
