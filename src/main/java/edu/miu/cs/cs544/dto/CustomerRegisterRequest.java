package edu.miu.cs.cs544.dto;


import lombok.Data;

@Data
public class CustomerRegisterRequest {
    String userName;
    String password;
    CustomerRequestDto customer;

}
