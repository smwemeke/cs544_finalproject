package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDto;
import edu.miu.cs.cs544.dto.orders.OrderResponse;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
    Reservation getReservationById(Integer id);
    OrderResponse createReservation(ReservationDto reservation);
    void deleteReservation(Integer id);

    //Reservation updateReservation(Integer id, Reservation reservation);
    OrderResponse updateReservation(Integer id, ReservationDto updatedReservationDto);

}