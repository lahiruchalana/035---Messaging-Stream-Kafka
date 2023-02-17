package com.example.messagingservice.business;

import com.example.messagingservice.dto.MessageDTO;

public interface MessageService {

    void sendMessage(MessageDTO messageDTO);
}
