package com.example.messagingservice.business.impl;

import com.example.messagingservice.business.UserDataService;
import com.example.messagingservice.dto.UserDataDTO;
import com.example.messagingservice.dto.UserDataOrganizedDTO;
import com.example.messagingservice.dto.response.ServiceResponseDTO;
import com.example.messagingservice.entity.UserData;
import com.example.messagingservice.repository.UserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDataImpl implements UserDataService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private UserDataRepository userDataRepository;

    public UserDataImpl(KafkaTemplate<String, String> kafkaTemplate, UserDataRepository userDataRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.userDataRepository = userDataRepository;
    }

    @Override
    public ServiceResponseDTO sendUserData(UserDataDTO userDataDTO) {
        log.info ("LOG :: UserDataImpl sendUserData()");
        UserDataOrganizedDTO userDataOrganizedDTO = new UserDataOrganizedDTO();
        userDataOrganizedDTO.setMessage("Name: " + userDataDTO.getFirst_name() + " " + userDataDTO.getLast_name() + " " +
                                        "Address: " + userDataDTO.getAddress() + " " +
                                        "Message: " + userDataDTO.getMessage() + " " +
                                        "Age: " + userDataDTO.getAge());
        kafkaTemplate.send("userDataTopic", "userDataTopic :: User data received: " + userDataOrganizedDTO.getMessage());
        UserData userData = new UserData();
        userData.setFirst_name(userDataDTO.getFirst_name());
        userData.setLast_name(userDataDTO.getLast_name());
        userData.setAddress(userDataDTO.getAddress());
        userData.setAge(userDataDTO.getAge());
        userData.setMessage(userDataDTO.getMessage());
        UserData userDataSave = userDataRepository.save(userData);
        ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();
        serviceResponseDTO.setData(userDataSave);
        serviceResponseDTO.setMessage("Success");
        serviceResponseDTO.setCode("200");
        serviceResponseDTO.setHttpStatus(HttpStatus.OK);
        return serviceResponseDTO;
    }
}
