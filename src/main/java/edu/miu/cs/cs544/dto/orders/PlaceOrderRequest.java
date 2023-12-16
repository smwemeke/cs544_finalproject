package edu.miu.cs.cs544.dto.orders;

import lombok.Data;

import java.util.List;
@Data
public class PlaceOrderRequest {

    private String customerId;

    private List<CreateItemRequest> items;
}
