package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Reservation;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
    Reservation getReservationById(Integer id);
    Reservation createReservation(Reservation reservation);
    void deleteReservation(Integer id);
}