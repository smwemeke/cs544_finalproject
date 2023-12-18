package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;
import edu.miu.cs.cs544.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Yenatfanta
 */
@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestParam("id") int id,
                                           @RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("excerpt") String excerpt,
                                           @RequestParam("rate" ) double rate,
                                           @RequestParam("maxCapacity" )int capacity,
                                           @RequestParam("type")ProductType type,
                                           @RequestParam("isAvailable") boolean isAvailable
                                           ){
      ProductResponse productResponse = productService.createProduct(id,name,description,excerpt,rate,capacity,type,isAvailable);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

    }
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id){
        ProductResponse productResponse = productService.getProduct(id);
       return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK) ;
    }
    @GetMapping("/products")
    public ResponseEntity<?> getProducts(){
        Collection<ProductResponse> productList = productService.getAllProducts();
        return new ResponseEntity<Collection<ProductResponse>>(productList,HttpStatus.OK);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
       ProductResponse deleted = productService.deleteProduct(id);

        if (deleted!=null) {
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

}
