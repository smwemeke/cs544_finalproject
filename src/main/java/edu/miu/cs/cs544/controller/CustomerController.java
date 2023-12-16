package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.CustomerRegisterRequest;
import edu.miu.cs.cs544.dto.CustomerResponseDto;
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

var response = customerService.register(request);
        return new ResponseEntity<CustomerResponseDto>(response, HttpStatus.OK) ;
//        return new ResponseEntity<>(, HttpStatus.OK);
    }


    //Custeromer Orders
    @GetMapping("/products")
    public ResponseEntity<?> listAllAvailableProducts(@PathParam("type") ProductType type, @PathParam("page") Integer page, @PathParam("len") Integer len){
        return new ResponseEntity<>(customerService.listAllProduct(new ListProductRequest(type), true), HttpStatus.OK) ;
    }


}
