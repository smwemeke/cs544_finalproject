package edu.miu.cs.cs544.dto.orders;

import edu.miu.cs.cs544.domain.Item;

import java.time.LocalDate;

public class ItemResponse {
    private Integer id;

    private Integer occupants;

    private LocalDate checkinDate;

    private LocalDate checkoutDate;

    private Double rate;

    private Integer unit;

    public ItemResponse buildFromDomain(Item i){
        id=i.getId();
        occupants =i.getOccupants();
        checkinDate = i.getCheckinDate();
        checkoutDate =i.getCheckoutDate();
        rate= i.getRate();
        unit = i.getUnit();
        return this;
    }
}
