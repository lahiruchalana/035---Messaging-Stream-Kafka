package com.example.messagingservice.business.impl;

import com.example.messagingservice.business.MessageService;
import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.entity.Message;
import com.example.messagingservice.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
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
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        log.info ("LOG :: MessageServiceImpl sendMessage()");
        kafkaTemplate.send("messageTopic", "messageTopic :: Message received: " + messageDTO.getMessage());
        Message message = convertDTOToMessage(messageDTO);
        Message messageSave = messageRepository.save(message);
        return convertMessageToDTO(messageSave);
    }

    public static MessageDTO convertMessageToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setMessage(message.getMessage());
        messageDTO.setUserId(message.getUserId());
        return messageDTO;
    }

    public static Message convertDTOToMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setMessage(messageDTO.getMessage());
        message.setUserId(messageDTO.getUserId());
        return message;
    }
}
