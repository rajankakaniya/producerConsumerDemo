package com.rajankakaniya.producerconsumerdemo.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value(value = "${demo.topic.name}")
    private String topicName;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/v1/producer/sendMessage")
    public String sendMessage(@RequestParam String message) {
        kafkaTemplate.send(topicName, message);
        return "Message sent successfully!";
    }
}

