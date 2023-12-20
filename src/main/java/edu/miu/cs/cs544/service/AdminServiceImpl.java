package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.orders.StateChangeRequest;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.OrderRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CustomerRepository customerRepository;

        @Override
    @Transactional
    public boolean checkIn(StateChangeRequest request) {
        var order = reservationRepository.findByItemsId(request.getItemId());
        if(!order.getCustomer().getId().equals(request.getCustomerId()))
            return false;
        Item item = order.getItems().stream().filter(i->i.getId().equals( request.getItemId())).findFirst().get();
        item.setCheckinDate(LocalDate.now());
        order.setState(ReservationState.Arrived);
            reservationRepository.save(order);
        return true;
    }

    @Override
    @Transactional
    public boolean checkOut(StateChangeRequest request) {
        var order = reservationRepository.findByItemsId(request.getItemId());
        if(!order.getCustomer().getId().equals(request.getCustomerId()))
            return false;
        Item item = order.getItems().stream().filter(i->i.getId().equals( request.getItemId())).findFirst().get();
        order.setState(ReservationState.Departed);
        item.setCheckoutDate(LocalDate.now());
        item.getProduct().setAvailable(true);
        reservationRepository.save(order);
        return true;
    }
    @Override
    @Transactional
    public boolean existsById(int customerId) {
        Optional<Customer> customer = Optional.of(customerRepository.findCustomerById(customerId));
        return customer.isPresent();
    }
}


