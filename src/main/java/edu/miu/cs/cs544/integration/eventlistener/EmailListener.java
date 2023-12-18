package edu.miu.cs.cs544.integration.eventlistener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailListener {

    @EventListener
    public void onEmailSendRequest(SendEmailEvent event){
        System.out.println("Email is Going to send to: "+event.getEmailAddress() + " => Message:" + event.getMessage());
    }
}
