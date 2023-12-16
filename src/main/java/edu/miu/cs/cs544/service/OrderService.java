package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.orders.OrderResponse;

public interface OrderService {
OrderResponse getOrder(Integer orderId);
}
