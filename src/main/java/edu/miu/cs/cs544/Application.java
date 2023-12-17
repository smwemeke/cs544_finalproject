package edu.miu.cs.cs544;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.dto.LoginRequest;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EntityScan("edu.miu.cs.cs544.domain")
@EnableJpaRepositories("edu.miu.cs.cs544.repository")
public class Application implements CommandLineRunner {
    @Autowired
    UserRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    LoginService loginService;
    @Autowired
    CustomerRepository customerRepository;
    static ApplicationContext context;

    public static void main(String[] args) {

        context = SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Address physicalAddress = new Address();
        physicalAddress.setCity("City1");
        physicalAddress.setType(AddressType.PHYSICAL);
        Address billing = new Address();
        billing.setCity("City1");
        billing.setType(AddressType.BILLING);

        Customer customer = new Customer();
        customer.setEmail("sample@email.com");
        customer.setFirstName("firstName");
        customer.setLastName("Lastname");
        customer.setPhysicalAddress(physicalAddress);
        customer.setBillingAddress(billing);

        customerRepository.save(customer);

        repository.save(new User("user1", encoder.encode("123456789"), UserType.CUSTOMER));
        var response = loginService.login(new LoginRequest("user1", "123456789"));
        System.out.println(response);

        //Create Product 1
        productRepository.save(Product.builder().name("Product1").maxCapacity(3).rate(250.0).build());
    }
}
