package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.orders.CreateItemRequest;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderResponse getOrder(Integer orderId);

    OrderResponse placeOrder(PlaceOrderRequest orderRequest);

    boolean isAvailable(LocalDate date, List<CreateItemRequest> items);
}
