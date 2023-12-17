package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.PaymentDto;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody PaymentDto paymentDto){
        paymentService.makePayment(paymentDto);
        return  ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping({"/{reservationId}"})
    public  ResponseEntity<?> getPaymentsForReservation(@PathVariable Integer reservationId){
        return new ResponseEntity<>(paymentService.getPaymentsForReservation(reservationId),HttpStatus.OK);
    }
}
