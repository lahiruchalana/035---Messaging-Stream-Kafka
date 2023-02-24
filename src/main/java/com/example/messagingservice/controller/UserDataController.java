package com.example.messagingservice.controller;

import com.example.messagingservice.business.UserDataService;
import com.example.messagingservice.dto.UserDataDTO;
import com.example.messagingservice.dto.response.ServiceResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/data")
@Slf4j
public class UserDataController {

    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/send")
    public ServiceResponseDTO sendUserData(
            @RequestBody UserDataDTO userDataDTO
    ) {
        log.info ("LOG :: UserDataController sendUserData()");
        try {
            log.info ("LOG :: UserDataController sendUserData() inside try");
            return userDataService.sendUserData(userDataDTO);
        } catch (Exception e) {
            log.warn("LOG::Inside the UserDataController sendUserData() Exception :: " + e.getMessage());
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
