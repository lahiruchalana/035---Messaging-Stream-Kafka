package com.example.messagingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String address;
    private Integer age;
    private String message;
}
