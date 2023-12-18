package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationAdapter;
import edu.miu.cs.cs544.dto.ReservationDto;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @Author: Ephrem
 *
 */

@RestController
@RequestMapping("/admin_res")

public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;
    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation == null) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {

        return new ResponseEntity<>(reservationService.createReservation(reservationDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Integer id, @RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservationDto = reservationService.updateReservation(id, reservationDto);
        if (updatedReservationDto == null) {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedReservationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id) {
        Reservation existingReservation = reservationService.getReservationById(id);
        if (existingReservation == null) {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
