package edu.miu.cs.cs544.cs544.controller;

import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yenatfanta
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
   @Autowired
    MockMvc mockMvc;
   @MockBean
    ProductService productService;
   @MockBean
    ProductRepository productRepository;

    @Test
    public void testGetProduct() throws Exception{
//        Mockito.when(productService.getProduct(1)).thenReturn(new ProductResponse( "TestProduct", "Description", "Excerpt", 10.0, 100, ProductType.Room,true));
//        mockMvc.perform(MockMvcRequestBuilders.get("product"))
//                .andExpect(status().isOk())
//                //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TestProduct"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.excerpt").value("Excerpt"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.0))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(100))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.productType").value("Room"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(true));
    }


}
