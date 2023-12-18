package edu.miu.cs.cs544.cs544.repository;

import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ReservationRepository repository;

    @Test
    public void findByAccountHolder_test_passed() throws Exception {


    }
}
