package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.orders.StateChangeRequest;
import edu.miu.cs.cs544.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdminService {

    boolean checkIn(StateChangeRequest request);
    boolean checkOut(StateChangeRequest request);
    boolean existsById(int customerId);
}
