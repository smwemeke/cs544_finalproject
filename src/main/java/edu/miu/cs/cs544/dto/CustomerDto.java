package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer id;


    private String firstName;

    private String lastName;

    private String email;
    private AuditData auditData;

    private Address physicalAddress;

    private Address billingAddress;
    private User user;
}
