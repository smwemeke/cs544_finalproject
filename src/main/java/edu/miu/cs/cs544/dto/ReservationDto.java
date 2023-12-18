package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.ReservationState;
import edu.miu.cs.cs544.dto.orders.CreateItemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class ReservationDto {

    private Integer customerId;

    private LocalDate reservationDate;

    private List<CreateItemRequest> items;


}