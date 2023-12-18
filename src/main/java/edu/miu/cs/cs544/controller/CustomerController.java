package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.*;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
    @PostMapping("/hello")
    public ResponseEntity<?> hello() {

        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerDto request) {
        var response = customerService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody CustomerDto request){

        CustomerDto response = customerService.updateCustomer(Integer.valueOf(id), request);
        if (response != null) return new ResponseEntity<>(response, HttpStatus.OK);
        else return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_MODIFIED.value(), "There is no customer with the provided ID"), HttpStatus.NOT_MODIFIED);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id){
        CustomerDto customerDto = customerService.getCustomerById(id);

        if(customerDto!=null){
            return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
        }
        else return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<?> getCustomerlastName(@PathVariable String lastName){
        CustomerDto customerDto = customerService.getCustomerByLastName(lastName);
        if(customerDto!=null){
            return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
        }
        else return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeCustomerByID(@PathVariable String id){
        CustomerDto customerDto = customerService.removeCustomerById(Integer.valueOf(id));
        if(customerDto!=null){
            return new ResponseEntity<>(customerDto, HttpStatus.FOUND);
        }
        else return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User with this ID not found"), HttpStatus.NOT_FOUND);
    }
    // Note Completed
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id, @RequestBody Object ob){
        return new ResponseEntity<>(new CustomerDto(), HttpStatus.OK);
    }


}
