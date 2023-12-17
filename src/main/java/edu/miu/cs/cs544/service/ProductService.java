package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;

import java.util.List;

/**
 * @author Yenatfanta
 */
public interface ProductService {

    ProductResponse createProduct(String name, String description, String excerpt, double rate, int maxCapacity, ProductType type);
    ProductResponse deleteProduct(int id);
    ProductResponse getProduct(int id);
    List<ProductResponse> getAllProducts();
  //  ProductResponse updateProduct(int id,String name, String description, String excerpt, double rate, int maxCapacity, ProductType type);

}
