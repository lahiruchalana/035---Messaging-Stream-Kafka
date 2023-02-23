package com.example.messagingservice.business.impl;

import com.example.messagingservice.business.UserDataService;
import com.example.messagingservice.dto.UserDataDTO;
import com.example.messagingservice.dto.UserDataOrganizedDTO;
import com.example.messagingservice.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDataImpl implements UserDataService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public UserDataDTO sendUserData(UserDataDTO userDataDTO) {
        log.info ("LOG :: UserDataImpl sendUserData()");
        UserDataOrganizedDTO userDataOrganizedDTO = new UserDataOrganizedDTO();
        userDataOrganizedDTO.setMessage("Name: " + userDataDTO.getFirst_name() + " " + userDataDTO.getLast_name() + " " +
                                        "Address: " + userDataDTO.getAddress() + " " +
                                        "Message: " + userDataDTO.getMessage() + " " +
                                        "Age: " + userDataDTO.getAge());
        kafkaTemplate.send("userDataTopic", "userDataTopic :: User data received: " + userDataOrganizedDTO.getMessage());
        Message message = convertDTOToMessage(messageDTO);
        Message messageSave = messageRepository.save(message);
        return convertMessageToDTO(messageSave);
    }
}
