package edu.miu.cs.cs544;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.dto.LoginRequest;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EntityScan("edu.miu.cs.cs544.domain")
@EnableJpaRepositories("edu.miu.cs.cs544.repository")
public class Application implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

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



        Country country = new Country();
        country.setCode("US");
        country.setName("America");
        country.setPopulation(10000);

        State state = new State();
        state.setCode("IA");
        state.setCountry(country);

        Address physicalAddress = new Address();
        physicalAddress.setCity("Fairfield");
        physicalAddress.setType(AddressType.PHYSICAL);
        physicalAddress.setState(state);

        Address billing = new Address();
        billing.setCity("Fairfield");
        billing.setType(AddressType.BILLING);
        billing.setState(state);
        billing.setLine1("line1");
        billing.setPostalCode("44444");


        Customer customer = new Customer();
        customer.setEmail("alemu@email.com");
        customer.setFirstName("Alemu");
        customer.setLastName("Teshome");
        customer.setPhysicalAddress(physicalAddress);
        customer.setBillingAddress(billing);

        User user = new User("AlemuTeshome",encoder.encode("123456789"),true, UserType.CUSTOMER, null);
        customer.setUser(user);
        customerRepository.save(customer);
        //userRepository.save(user);
        var response = loginService.login(new LoginRequest("AlemuTeshome","123456789"));
        System.out.println(response);

        //Create Product 1
        //productRepository.save(Product.builder().name("Product1").maxCapacity(3).rate(250.0).build());
    }
}
