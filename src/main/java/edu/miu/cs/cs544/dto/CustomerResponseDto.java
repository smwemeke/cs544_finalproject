package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Customer;
import lombok.Data;

@Data
public class CustomerResponseDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    public CustomerResponseDto(Customer c) {
        setValue(c);
    }
    public CustomerResponseDto() {

    }
    private void setValue(Customer c){
        id = c.getId();
        firstName = c.getFirstName();
        lastName = c.getLastName();
        email = c.getEmail();
    }
    public CustomerResponseDto buildFromDomain(Customer c) {
       setValue(c);
        return this;
    }

}
