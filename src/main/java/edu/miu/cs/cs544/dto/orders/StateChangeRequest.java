package edu.miu.cs.cs544.dto.orders;

import lombok.Data;

@Data
public class StateChangeRequest {
    Integer customerId;
    Integer itemId;

    public StateChangeRequest(Integer customerId, Integer itemId) {
        this.customerId = customerId;
        this.itemId = itemId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
