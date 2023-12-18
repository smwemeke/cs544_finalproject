package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.ReservationState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ReservationDto {

    private Integer id;

    private Integer customerId;

    private LocalDate reservationDate;

    private ReservationState state;

    private List<Item> itemIds;

    private AuditData auditData;

}