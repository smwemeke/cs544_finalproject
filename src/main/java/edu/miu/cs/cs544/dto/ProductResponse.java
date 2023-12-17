package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import jakarta.persistence.Enumerated;

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
}
