package edu.miu.cs.cs544;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.LoginRequest;
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
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    LoginService loginService;
   static ApplicationContext context;

    public static void main(String[] args) {

        context = SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new User("user1",encoder.encode("123456789"), UserType.CUSTOMER));
        var response = loginService.login(new LoginRequest("user1","123456789"));
        System.out.println(response);
    }
}
