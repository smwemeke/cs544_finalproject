package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
    Reservation getReservationById(Integer id);
    ReservationDto createReservation(ReservationDto reservation);
    void deleteReservation(Integer id);

    //Reservation updateReservation(Integer id, Reservation reservation);
    ReservationDto updateReservation(Integer id, ReservationDto updatedReservationDto);

}