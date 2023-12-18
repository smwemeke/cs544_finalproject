package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.orders.OrderResponse;

public class ReservationAdapter {
    public static OrderResponse getReservationDto(Reservation reservation) {
        return new OrderResponse().buildFromDomain(reservation);
    }
//    public static Reservation getReservation(ReservationDto reservationDto) {
//        return Reservation.builder().customer(reservationDto.getCustomerId()).reservationDate(reservationDto.getReservationDate()).build();
//    }
}
