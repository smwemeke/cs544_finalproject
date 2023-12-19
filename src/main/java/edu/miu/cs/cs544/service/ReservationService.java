package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDto;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.ReservationResponse;

import java.util.List;

public interface ReservationService {

    List<ReservationResponse> getAllReservations();
    ReservationResponse getReservationById(Integer id);
    ReservationResponse createReservation(ReservationDto reservation);
    void deleteReservation(Integer id);

    //Reservation updateReservation(Integer id, Reservation reservation);
    ReservationResponse updateReservation(Integer id, ReservationDto updatedReservationDto);

}