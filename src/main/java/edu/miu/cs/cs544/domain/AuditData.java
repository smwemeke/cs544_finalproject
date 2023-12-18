package edu.miu.cs.cs544.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.cglib.core.Local;

@Embeddable
@Data
public class AuditData {

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public AuditData() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }


    public AuditData(String createdBy, String updatedBy) {
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
