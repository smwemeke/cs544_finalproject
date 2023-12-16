package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.CustomerRegisterRequest;
import edu.miu.cs.cs544.dto.ListProductRequest;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.dto.orders.StateChangeRequest;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;

    @PostMapping("/hello")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> register(@RequestBody CustomerRegisterRequest request){

        return new ResponseEntity<>(customerService.register(request), HttpStatus.OK) ;
    }


    //Custeromer Orders
    @GetMapping("/products")
    public ResponseEntity<?> listAllAvailableProducts(@PathParam("type") ProductType type, @PathParam("page") Integer page, @PathParam("len") Integer len){
        return new ResponseEntity<>(customerService.listAllProduct(new ListProductRequest(type, page, len), true), HttpStatus.OK) ;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Integer orderId){

        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK) ;
    }
    @GetMapping("/orders/available/{productId}")
    public ResponseEntity<?> checkProductAvailable(@PathVariable Integer productId){
         return new ResponseEntity<>(customerService.isAvailable(productId), HttpStatus.OK) ;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderRequest request){
        //TODO Check product available

        return new ResponseEntity<>(customerService.placeOrder(request), HttpStatus.OK) ;
    }


    @PostMapping("/orders/items/{itemId}/checkins")
    public ResponseEntity<?> checkIn(@PathVariable int itemId,@RequestBody StateChangeRequest request){
        //TODO validate customer validat for this item

        return new ResponseEntity<>(customerService.checkIn(request), HttpStatus.OK) ;
    }

    @PostMapping("/orders/items/{itemId}/checkouts")
    public ResponseEntity<?> checkOut(@PathVariable int itemId,@RequestBody StateChangeRequest request){
        //TODO validate customer validat for this item

        return new ResponseEntity<>(customerService.checkOut(request), HttpStatus.OK) ;
    }

    @PostMapping("/orders/items/{itemId}/cancels")
    public ResponseEntity<?> cancel(@PathVariable int itemId,@RequestBody StateChangeRequest request){
        //TODO validate customer validat for this item

        return new ResponseEntity<>(customerService.cancel(request), HttpStatus.OK) ;
    }
}
