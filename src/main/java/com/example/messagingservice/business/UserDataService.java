package com.example.messagingservice.business;

import com.example.messagingservice.dto.UserDataDTO;

public interface UserDataService {

    UserDataDTO sendUserData(UserDataDTO userDataDTO);
}
