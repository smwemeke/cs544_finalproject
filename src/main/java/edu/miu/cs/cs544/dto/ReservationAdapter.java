package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.ReservationResponse;

public class ReservationAdapter {
    public static ReservationResponse getReservationDto(Reservation reservation) {
        return new ReservationResponse().buildFromDomain(reservation);
    }
//    public static Reservation getReservation(ReservationDto reservationDto) {
//        return Reservation.builder().customer(reservationDto.getCustomerId()).reservationDate(reservationDto.getReservationDate()).build();
//    }
}
