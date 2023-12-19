package edu.miu.cs.cs544.dto.orders;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.CustomerResponseDto;
import lombok.Data;

import java.util.List;
@Data
public class ReservationResponse {

    private Integer id;

    private CustomerResponseDto customer;

    private ReservationState state;

    private List<ItemResponse> items;

    public ReservationResponse buildFromDomain(Reservation r){
        id=r.getId();
        state =r.getState();
        customer = new CustomerResponseDto().buildFromDomain(r.getCustomer());
        items = r.getItems().stream().map(i->new ItemResponse().buildFromDomain(i)).toList();
        return this;
    }
}
