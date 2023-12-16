package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.ProductType;

public class ListProductRequest extends Pageable {
    ProductType type;

    public ListProductRequest(ProductType type, Integer page, Integer len) {
        super( page, len);
        this.type = type;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
