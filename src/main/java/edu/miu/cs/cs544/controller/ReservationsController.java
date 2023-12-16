package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {
    @Autowired
    OrderService orderService;


    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Integer orderId){

        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK) ;
    }
//    @GetMapping("/available/{productId}")
//    public ResponseEntity<?> checkProductAvailable(@PathVariable Integer productId){
//        return new ResponseEntity<>(customerService.isAvailable(productId), HttpStatus.OK) ;
//    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderRequest request){
        //TODO Check product available

        return new ResponseEntity<>(orderService.placeOrder(request), HttpStatus.OK) ;
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
