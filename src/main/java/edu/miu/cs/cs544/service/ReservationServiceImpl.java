package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationAdapter;
import edu.miu.cs.cs544.dto.ReservationDto;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

//    @Override
//    public ReservationDto createReservation(ReservationDto reservationDto) {
//        Reservation reservation = ReservationAdapter.getReservation(reservationDto);
//    //  Customer customer = customerRepository.findById(reservationDto.getCustomerId()).orElse(null);
//        Customer customer = new Customer();
//        customer.setId(reservationDto.getCustomerId());
//        reservation.setCustomer(customer);
//        return ReservationAdapter.getReservationDto(reservationRepository.save(reservation));
//    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = ReservationAdapter.getReservation(reservationDto);
        Customer customer = customerRepository.findById(reservationDto.getCustomerId()).orElse(null);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with id " + reservationDto.getCustomerId() + " does not exist");
        }
        reservation.setCustomer(customer);
        return ReservationAdapter.getReservationDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDto updateReservation(Integer id, ReservationDto updatedReservationDto) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (!optionalReservation.isPresent()) {
            return null;
        }
        Reservation existingReservation = optionalReservation.get();
        Reservation updatedReservation = ReservationAdapter.getReservation(updatedReservationDto);
        existingReservation.setReservationDate(updatedReservation.getReservationDate());
        existingReservation.setState(updatedReservation.getState());
        //existingReservation.setCustomer(updatedReservation.getCustomer());
        Reservation savedReservation = reservationRepository.save(existingReservation);
        return ReservationAdapter.getReservationDto(savedReservation);
    }
    @Override
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }
}