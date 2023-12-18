package edu.miu.cs.cs544.cs544.controller;

import edu.miu.cs.cs544.controller.ProductController;
import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;
import edu.miu.cs.cs544.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yenatfanta
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mock;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateProduct(){
        ProductResponse mockResponse = new ProductResponse("hotel","2 bed and 1 bath suite","",50.0, 3,ProductType.Room,true);
        ResponseEntity<?> responseEntity = productController.createProduct(1, "TestProduct", "Description", "Excerpt", 10.0, 100, ProductType.Room);


        verify(productService, times(1)).createProduct(1,"hotel","2 bed and 1 bath suite","",50.0, 3,ProductType.Room);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());

    }
}
