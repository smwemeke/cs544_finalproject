package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yenatfanta
 */
@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
//    @PostMapping("/createProduct")
//    public ResponseEntity<?> createProduct()
}
