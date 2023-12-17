package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;
import edu.miu.cs.cs544.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yenatfanta
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(String name, String description, String excerpt, double rate, int maxCapacity, ProductType type) {

        ProductResponse productResponse = new ProductResponse();
        Product product = Product.builder().name(name).description(description).excerpt(excerpt).rate(rate).maxCapacity(maxCapacity).type(type).build();
        productRepository.save(product);
        return productResponse.buildFromDomain(product);
    }

    @Override
    public ProductResponse deleteProduct(int id) {
        ProductResponse productResponse = new ProductResponse();
        ProductResponse pr = productResponse.buildFromDomain(productRepository.findById(id)) ;
        productRepository.deleteById(id);
        return pr;
    }

    @Override
    public ProductResponse getProduct(int id) {
        ProductResponse productResponse = new ProductResponse();
        Product product = productRepository.findById(id);
        return productResponse.buildFromDomain(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        ProductResponse productResponse = new ProductResponse();
        List<Product> products = productRepository.findAll();
        return products.stream().map(p-> new ProductResponse().buildFromDomain(p)).toList();
    }

//    @Override
//    public ProductResponse updateProduct(int id, String name, String description, String excerpt, double rate, int maxCapacity, ProductType type) {
//
//        return null;
//    }
}
