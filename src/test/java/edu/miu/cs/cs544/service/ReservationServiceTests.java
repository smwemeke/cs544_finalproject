package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.Application;
import edu.miu.cs.cs544.config.SecurityConfig;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.orders.CreateItemRequest;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.LoginService;
import edu.miu.cs.cs544.service.OrderService;
import edu.miu.cs.cs544.service.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
//@ComponentScan("edu.miu.cs.cs544")

public class ReservationServiceTests {
    @MockBean
    private KafkaTemplate<String, String> kafkaTemplateMock;

    @TestConfiguration
    static class OrderServiceImplTestContextConfiguration {
        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl();
        }
    }

    @MockBean
    private PasswordEncoder encoder;
    @MockBean
    LoginService loginService;

    @Autowired
    TestEntityManager entityManager;

    @MockBean
    ReservationRepository repository;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    OrderService service;

    @Test
    public void placeOrder_test() {
        Customer mockCustomer = Customer.builder().id(1).firstName("cust1").build();
        Product mockProduct1 = entityManager.persist(Product.builder().name("p1").build());
        Product mockProduct2 = entityManager.persist(Product.builder().name("p2").build());
        Product mockProduct3 = entityManager.persist(Product.builder().name("p3").build());

        Mockito.when(customerRepository.getReferenceById(1)).thenReturn(mockCustomer);
        Mockito.when(productRepository.getReferenceById(1)).thenReturn(mockProduct1);
        Mockito.when(productRepository.getReferenceById(2)).thenReturn(mockProduct2);
        Mockito.when(productRepository.getReferenceById(3)).thenReturn(mockProduct3);

        List<CreateItemRequest> items = new ArrayList<>();

        items.add(CreateItemRequest.builder().productId(1).build());
        items.add(CreateItemRequest.builder().productId(2).build());
        items.add(CreateItemRequest.builder().productId(3).build());
        var date = LocalDate.now();

        var request = new PlaceOrderRequest();

        request.setCustomerId(1);
        request.setReservationDate(date);
        request.setItems(items);

        var result = service.placeOrder(request);

        var order = entityManager.find(Reservation.class, result.getId());
        assertNotNull(order);


    }
}
