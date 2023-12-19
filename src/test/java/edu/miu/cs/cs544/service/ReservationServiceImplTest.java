package edu.miu.cs.cs544.service;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteReservation() {

        Integer id = 1;

        reservationService.deleteReservation(id);

        verify(reservationRepository, times(1)).deleteById(id);
    }



    @Test
    public void testGetAllReservations() {
        reservationService.getAllReservations();

        verify(reservationRepository, times(1)).findAll();
    }



}