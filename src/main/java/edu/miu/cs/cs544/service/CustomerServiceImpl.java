package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.*;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.StateChangeRequest;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.OrderRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;
    @Override
    public CustomerResponseDto register(CustomerRegisterRequest request) {

        return new CustomerResponseDto();
    }

    @Override
    public OrderResponse placeOrder(PlaceOrderRequest request) {

        return null;
    }

    @Override
    public boolean isAvailable(int productId) {
        return false;
    }

    @Override
    public List<ProductResponse> listAllProduct(ListProductRequest request, boolean isAvailable) {
        return productRepository.findByIsAvailableAndType(isAvailable, request.getType()).stream().map(p->new ProductResponse().buildFromDomain(p)).toList();
    }

    @Override
    @Transactional
    public boolean checkIn(StateChangeRequest request) {
        var order = orderRepository.findByItemsId(request.getItemId());
        if(!order.getCustomer().getId().equals(request.getCustomerId()))
            return false;
        Item item = order.getItems().stream().filter(i->i.getId().equals( request.getItemId())).findFirst().get();
        item.setCheckinDate(LocalDate.now());
        order.setState(ReservationState.Arrived);
        orderRepository.save(order);
        return true;
    }

    @Override
    @Transactional
    public boolean checkOut(StateChangeRequest request) {
        var order = orderRepository.findByItemsId(request.getItemId());
        if(!order.getCustomer().getId().equals(request.getCustomerId()))
            return false;
        Item item = order.getItems().stream().filter(i->i.getId().equals( request.getItemId())).findFirst().get();
        order.setState(ReservationState.Departed);
        item.setCheckoutDate(LocalDate.now());
        item.getProduct().setAvailable(true);
        orderRepository.save(order);
        return true;
    }

    @Override
    @Transactional
    public boolean cancel(StateChangeRequest request) {
        var order = orderRepository.findByItemsId(request.getItemId());
        if(!order.getCustomer().getId().equals(request.getCustomerId()))
            return false;
        Item item = order.getItems().stream().filter(i->i.getId().equals( request.getItemId())).findFirst().get();
        order.setState(ReservationState.Cancelled);
        item.getProduct().setAvailable(true);
        orderRepository.save(order);
        return true;
    }
}
