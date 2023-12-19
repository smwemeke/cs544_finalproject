package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationAdapter;
import edu.miu.cs.cs544.dto.ReservationDto;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public OrderResponse createReservation(ReservationDto reservationDto) {
        Reservation reservation =  new Reservation();
        Customer customer = customerRepository.findById(reservationDto.getCustomerId()).orElse(null);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with id " + reservationDto.getCustomerId() + " does not exist");
        }
        reservation.setCustomer(customer);
        reservation.setReservationDate(reservationDto.getReservationDate());
        var items = reservationDto.getItems().stream().map(i -> {
            var p = productRepository.getReferenceById(i.getProductId());
            var item = new Item().buildFromDto(i);
            item.setOrder(reservation);
            item.setProduct(p);
            return item;
        }).toList();
        reservation.setItems(items);
        var savedReservation = reservationRepository.save(reservation);

        return ReservationAdapter.getReservationDto(reservation);
    }

    @Override
    public OrderResponse updateReservation(Integer id, ReservationDto updatedReservationDto) {

        var reservation = reservationRepository.findById(id).get();
        reservation.setReservationDate(updatedReservationDto.getReservationDate());

        return new OrderResponse().buildFromDomain(reservation);
    }
    @Override
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }
}