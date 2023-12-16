package edu.miu.cs.cs544.dto.orders;

import java.util.List;

public class PlaceOrderRequest {

    private String customerId;

    private List<CreateItemRequest> items;
}
