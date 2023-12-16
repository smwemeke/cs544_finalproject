package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderResponse getOrder(Integer orderId) {
        return new OrderResponse().buildFromDomain(orderRepository.getReferenceById(orderId));
    }
}
