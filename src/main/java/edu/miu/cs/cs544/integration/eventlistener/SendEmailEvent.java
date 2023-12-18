package edu.miu.cs.cs544.integration.eventlistener;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
public class SendEmailEvent {
    private String emailAddress;
    private String message;
}
