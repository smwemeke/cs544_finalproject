package edu.miu.cs.cs544.dto.orders;

import lombok.Data;

@Data
public class CreateItemRequest {
    Integer productId;
    Double rate;
    Integer occupants;
    Integer unit;

}
