package edu.miu.cs.cs544.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateItemRequest {
    Integer productId;
    Double rate;
    Integer occupants;
    Integer unit;

}
