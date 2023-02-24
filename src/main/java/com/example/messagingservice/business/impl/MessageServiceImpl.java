package com.example.messagingservice.business.impl;

import com.example.messagingservice.business.MessageService;
import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.dto.response.ServiceResponseDTO;
import com.example.messagingservice.entity.Message;
import com.example.messagingservice.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private MessageRepository messageRepository;

    public MessageServiceImpl(KafkaTemplate<String, String> kafkaTemplate, MessageRepository messageRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageRepository = messageRepository;
    }

    @Override
    public ServiceResponseDTO sendMessage(MessageDTO messageDTO) {
        log.info ("LOG :: MessageServiceImpl sendMessage()");
        kafkaTemplate.send("messageTopic", "messageTopic :: Message received: " + messageDTO.getMessage());
        Message message = new Message();
        message.setMessage(messageDTO.getMessage());
        message.setUserId(messageDTO.getUserId());
        Message messageSave = messageRepository.save(message);
        ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();
        serviceResponseDTO.setData(messageSave);
        serviceResponseDTO.setMessage("Success");
        serviceResponseDTO.setCode("200");
        serviceResponseDTO.setHttpStatus(HttpStatus.OK);
        return serviceResponseDTO;
    }

}
