package edu.miu.cs.cs544.cs544.service;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author Yenatfanta
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    TestEntityManager entityManager;

    @Before
    public void setUp(){
        int id = 1;
        Product product = new Product( id,"TestProduct", "Description", "Excerpt", 10.0, 100, ProductType.Room,true);
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.when(productRepository.findById(id)).thenReturn(product);
    }

    @Test
    public void testCreateProduct(){
       int id = 1;
        Product created = new Product( id,"TestProduct", "Description", "Excerpt", 10.0, 100, ProductType.Room,true);
        assertThat(created.getId()).isEqualTo(id);
    }
}
