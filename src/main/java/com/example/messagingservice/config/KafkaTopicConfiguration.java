package com.example.messagingservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    /** Able to create topics here **/

    @Bean
    public NewTopic messageTopic() {
        return TopicBuilder
                .name("messageTopic")
                .build();
    }

    @Bean
    public NewTopic userDataTopic() {
        return TopicBuilder
                .name("userDataTopic")
                .partitions(3)
                .replicas(2)
                .build();
    }

}
