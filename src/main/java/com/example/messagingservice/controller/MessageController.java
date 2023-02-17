package com.example.messagingservice.controller;

import com.example.messagingservice.business.MessageService;
import com.example.messagingservice.dto.MessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public void sendMessage(
            @RequestBody MessageDTO messageDTO
            ) {
        messageService.sendMessage(messageDTO);
    }

}
