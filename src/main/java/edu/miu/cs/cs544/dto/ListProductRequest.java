package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductRequest {
    ProductType type;

    public ListProductRequest(ProductType type, Integer page, Integer len) {
    }
}
