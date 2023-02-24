package com.example.messagingservice.controller;

import com.example.messagingservice.business.MessageService;
import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.dto.response.ServiceResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ServiceResponseDTO sendMessage(
            @RequestBody MessageDTO messageDTO
            ) {
        log.info ("LOG :: MessageController sendMessage()");
        try {
            log.info ("LOG :: MessageController sendMessage() inside try");
            return messageService.sendMessage(messageDTO);
        } catch (Exception e) {
            log.warn("LOG::Inside the MessageController sendMessage() Exception :: " + e.getMessage());
            ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();
            serviceResponseDTO.setError(e);
            serviceResponseDTO.setMessage("Fail");
            serviceResponseDTO.setCode("500");
            serviceResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            serviceResponseDTO.setDescription(e.getMessage());
            return serviceResponseDTO;
        }
    }

}
