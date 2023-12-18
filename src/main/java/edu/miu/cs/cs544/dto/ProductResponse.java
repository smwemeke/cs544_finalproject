package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ProductResponse {
    private Integer id;

    private String name;

    private String description;

    private String excerpt;

    private Double rate;
    private boolean isAvailable;

//    public ProductResponse(String name, String description, String excerpt, Double rate, Integer maxCapacity, ProductType type,boolean isAvailable) {
//        this.name = name;
//        this.description = description;
//        this.excerpt = excerpt;
//        this.rate = rate;
//        this.maxCapacity = maxCapacity;
//        this.type = type;
//        this.isAvailable = isAvailable;
//    }
    public ProductResponse(){}
    private Integer maxCapacity;

    private ProductType type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

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
//    public List<ProductResponse> buildProductResponseListFromProductList(List<Product> products){
//        List<ProductResponse> productResponses = new ArrayList<>();
//        for(Product p: products){
//            productResponses.add(buildFromDomain(p));
//        }
//        return productResponses;
//    }
}
