package edu.miu.cs.cs544.dto.orders;

import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.CustomerResponseDto;
import lombok.Data;

import java.util.List;
@Data
public class OrderResponse {

    private Integer id;

    private CustomerResponseDto customer;

    private ReservationState state;

    private List<ItemResponse> items;

    public OrderResponse buildFromDomain(Reservation o){
        id=o.getId();
        state =o.getState();
        customer = new CustomerResponseDto().buildFromDomain(o.getCustomer());
        items = o.getItems().stream().map(i->new ItemResponse().buildFromDomain(i)).toList();
        return this;
    }
}
