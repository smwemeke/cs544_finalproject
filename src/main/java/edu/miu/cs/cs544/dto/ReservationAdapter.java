package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Reservation;

public class ReservationAdapter {
    public static ReservationDto getReservationDto(Reservation reservation) {
        return new ReservationDto(reservation.getId(), reservation.getCustomer().getId(),
                reservation.getReservationDate(), reservation.getState(),
                reservation.getItems(),reservation.getAuditData());
    }
    public static Reservation getReservation(ReservationDto reservationDto) {
        return new Reservation(reservationDto.getId(), null,reservationDto.getReservationDate(),
                reservationDto.getState(), reservationDto.getItemIds(),reservationDto.getAuditData());
    }
}
