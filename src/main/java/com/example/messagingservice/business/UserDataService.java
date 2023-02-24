package com.example.messagingservice.business;

import com.example.messagingservice.dto.UserDataDTO;
import com.example.messagingservice.dto.response.ServiceResponseDTO;

public interface UserDataService {

    ServiceResponseDTO sendUserData(UserDataDTO userDataDTO);
}
