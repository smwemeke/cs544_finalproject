package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Address;
import jakarta.persistence.ManyToOne;

public class CustomerRequestDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Address physicalAddress;

    private Address billingAddress;
}
