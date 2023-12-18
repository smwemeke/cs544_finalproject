package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Customer;

import java.util.Optional;

public class CustomerDtoAdapter {

    public static Customer toCustomer(CustomerDto customerDto) {
        if (customerDto==null) return null;
        return new Customer(customerDto.getFirstName(), customerDto.getLastName(),
                customerDto.getEmail(), customerDto.getAuditData(), customerDto.getPhysicalAddress(),
                customerDto.getBillingAddress(), customerDto.getUser());
    }

    public static CustomerDto toCustomerDto(Optional<Customer> customer){
        if(customer.isEmpty()) return null;
        else{
            Customer c = customer.get();
            return new CustomerDto(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getAuditData()
            , c.getPhysicalAddress(), c.getBillingAddress(), c.getUser());
        }
    }
}
