package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import jakarta.persistence.Enumerated;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {
    private Integer id;

    private String name;

    private String description;

    private String excerpt;

    private Double rate;
    private Integer maxCapacity;

    private ProductType type;

    public ProductResponse buildFromDomain(Product p){
        id= p.getId();
        name = p.getName();
        description = p.getDescription();
        excerpt =p.getExcerpt();
        rate =p.getRate();
        maxCapacity = p.getMaxCapacity();
        type= p.getType();
        return this;
    }
    public List<ProductResponse> buildProductResponseListFromProductList(List<Product> products){
        List<ProductResponse> productResponses = new ArrayList<>();
        products.stream().forEach(p->productResponses.add(buildFromDomain(p)));
        return productResponses;
    }
}
