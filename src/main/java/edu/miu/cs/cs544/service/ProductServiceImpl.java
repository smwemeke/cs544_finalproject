package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.dto.ProductResponse;
import edu.miu.cs.cs544.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Yenatfanta
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductResponse productResponse){
        Product product = new Product();
        product.setName(productResponse.getName());
        product.setDescription(productResponse.getDescription());
        product.setRate(productResponse.getRate());
        product.setMaxCapacity(productResponse.getMaxCapacity());
        product.setExcerpt(productResponse.getExcerpt());
        product.setAvailable(productResponse.isAvailable());
        product.setType(productResponse.getType());
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

        List<ProductResponse> products = productRepository.findAll().stream().map(p->new ProductResponse().buildFromDomain(p)).toList();
       return products;

    }
    @Override
    public ProductResponse updateProduct(int id,ProductResponse productResponse){
        Product product = productRepository.findById(id);
        if(product!=null){
            product.setName(productResponse.getName());
            product.setDescription(productResponse.getDescription());
            product.setRate(productResponse.getRate());
            product.setMaxCapacity(productResponse.getMaxCapacity());
            product.setExcerpt(productResponse.getExcerpt());
            product.setAvailable(productResponse.isAvailable());
            product.setType(productResponse.getType());
            productRepository.save(product);
        }
        return productResponse.buildFromDomain(product);
    }
    public List<ProductResponse> findAvailableProducts(){
        List<ProductResponse> availableProducts = new ArrayList<>();
        for(ProductResponse pr: getAllProducts()){
            if(pr.isAvailable()){
                availableProducts.add(pr);
            }
        }
        return availableProducts;
    }

}
