package com.example.messagingservice.controller;

import com.example.messagingservice.business.MessageService;
import com.example.messagingservice.controller.controllconfig.ResponseHandler;
import com.example.messagingservice.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
@Slf4j
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<Object> sendMessage(
            @RequestBody MessageDTO messageDTO
            ) {
        log.info ("LOG :: MessageController sendMessage()");
        try {
            log.info ("LOG :: MessageController sendMessage() inside try");
            MessageDTO messageDTOSaved = messageService.sendMessage(messageDTO);
            return ResponseHandler.responseBuilder("Success", "2000", HttpStatus.OK, messageDTOSaved);
        } catch (Exception e) {
            log.warn("LOG::Inside the DeliveryController saveDelivery Exception :: " + e.getMessage());
            return ResponseHandler.responseBuilder("Fail", "5000", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
