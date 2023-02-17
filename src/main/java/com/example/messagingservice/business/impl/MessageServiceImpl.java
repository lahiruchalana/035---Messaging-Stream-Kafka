package com.example.messagingservice.business.impl;

import com.example.messagingservice.business.MessageService;
import com.example.messagingservice.dto.MessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(MessageDTO messageDTO) {
        kafkaTemplate.send("messageTopic", "messageTopic :: Message received: " + messageDTO.getMessage());
    }
}
