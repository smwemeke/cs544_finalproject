package edu.miu.cs.cs544.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;
   @ManyToOne
    private Reservation reservation;
   private double amount;
   private LocalDate paymentDate;
}
