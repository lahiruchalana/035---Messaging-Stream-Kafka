package com.example.messagingservice.business;

import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.dto.response.ServiceResponseDTO;

public interface MessageService {

    ServiceResponseDTO sendMessage(MessageDTO messageDTO);
}
