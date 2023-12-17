package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.orders.CreateItemRequest;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.OrderRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public OrderResponse getOrder(Integer orderId) {
        return new OrderResponse().buildFromDomain(orderRepository.getReferenceById(orderId));
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(PlaceOrderRequest orderRequest) {
        var res = new Reservation();
        res.setReservationDate(orderRequest.getReservationDate());
        res.setCustomer(customerRepository.getReferenceById(orderRequest.getCustomerId()));
        var items = orderRequest.getItems().stream().map(i -> {
            var p = productRepository.getReferenceById(i.getProductId());
            var item = new Item().buildFromDto(i);
            item.setOrder(res);
            item.setProduct(p);
            return item;
        }).toList();
        res.setItems(items);
        orderRepository.save(res);
        return new OrderResponse().buildFromDomain(res);
    }

    @Override
    public boolean isAvailable(LocalDate date, List<CreateItemRequest> items) {
        var orders = orderRepository.findByReservationDateAndItemsProductIdIn(date, items.stream().map(i->i.getProductId()).toList());
        var count = orders.stream().filter(o->o.getState() != ReservationState.Cancelled && o.getState()!= ReservationState.Departed).count();
        return count==0;
    }
}
