package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.ReservationState;
import java.time.LocalDate;
import java.util.List;

public class ReservationDto {

    private Integer id;

    private Integer customerId;

    private LocalDate reservationDate;

    private ReservationState state;

    private List<Integer> itemIds;

    // getters and setters
}