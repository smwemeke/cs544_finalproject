package edu.miu.cs.cs544.controller;


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
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductResponse productResponse){
        productService.createProduct(productResponse);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id){
        ProductResponse productResponse = productService.getProduct(id);
       return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK) ;
    }
    @GetMapping
    public ResponseEntity<?> getProducts(){
        Collection<ProductResponse> productList = productService.getAllProducts();
        return new ResponseEntity<Collection<ProductResponse>>(productList,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
       ProductResponse deleted = productService.deleteProduct(id);

        if (deleted!=null) {
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")int id,@RequestBody ProductResponse productResponse){
        productService.updateProduct(id,productResponse);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

}
