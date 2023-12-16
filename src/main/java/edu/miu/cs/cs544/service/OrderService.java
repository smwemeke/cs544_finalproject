package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;

public interface OrderService {
    OrderResponse getOrder(Integer orderId);

    OrderResponse placeOrder(PlaceOrderRequest orderRequest);
}
