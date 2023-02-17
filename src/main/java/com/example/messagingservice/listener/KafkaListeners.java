package com.example.messagingservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    /** Implementing Kafka Listener here
     * Using the Kafka Listener
     * We can access to data
     * of the relevant topic
     * and the relevant groupId
     * **/

    @KafkaListener(
            topics = "message",
            groupId = "anyGroupId"
    )
    void listener(String data) {
        System.out.println("messageTopic :: Listener received: " + data );
    }

}
