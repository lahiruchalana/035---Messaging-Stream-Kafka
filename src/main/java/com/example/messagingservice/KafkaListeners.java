package com.example.messagingservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "message",
            groupId = "anyGroupId"
    )
    void listener(String data) {
        System.out.println("Listener received: " + data );
    }

}
