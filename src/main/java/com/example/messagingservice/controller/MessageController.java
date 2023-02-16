package com.example.messagingservice.controller;

import com.example.messagingservice.dto.MessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public void publish(
            @RequestBody MessageDTO messageDTO
            ) {
        kafkaTemplate.send("message", "Message: " + messageDTO.getMessage());
    }
}
