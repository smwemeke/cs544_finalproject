package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author Yenatfanta
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductServiceTest {
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    TestEntityManager entityManager;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    LoginService loginService;
    @MockBean
    PasswordEncoder encoder;
    @MockBean
    UserRepository userRepository;


    @Before
    public void setUp(){
        int id = 1;

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(id);

        Mockito.when(productService.getProduct(id)).thenReturn(productResponse);
    }

    @Test
    public void testGetProduct(){
       int id = 1;
        ProductResponse found = productService.getProduct(id);
        assertThat(found.getId()).isEqualTo(id);
    }

}
