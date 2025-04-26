package com.location.exceptionhandler;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.location.exception.ResourceNotFoundException;

@Controller
public class WebSocketExceptionHandler {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketExceptionHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    @MessageExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException(ResourceNotFoundException ex) {
        messagingTemplate.convertAndSend("/topic/error", ex.getMessage()); 
    }

    @MessageExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        messagingTemplate.convertAndSend("/topic/error", ex.getMessage()); 
    }
}
