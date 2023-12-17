package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Reservation,Integer> {
    Reservation findByItemsId(Integer itemId);

    List<Reservation> findByReservationDateAndItemsProductIdIn(LocalDate date, List<Integer> productIds);
}
