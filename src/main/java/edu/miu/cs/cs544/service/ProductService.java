package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;

import java.util.List;

/**
 * @author Yenatfanta
 */
public interface ProductService {

    ProductResponse createProduct(ProductResponse productResponse);
    ProductResponse updateProduct(int id,ProductResponse productResponse);
    ProductResponse deleteProduct(int id);
    ProductResponse getProduct(int id);
    List<ProductResponse> getAllProducts();



}
