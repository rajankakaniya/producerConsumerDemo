package com.rajankakaniya.producerconsumerdemo.consumer;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Getter
@Setter
public class MessageConsumer {

    private int successCount = 0;
    private int errorCount = 0;

    @KafkaListener(topics = "${demo.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            String msg = record.value();
            if(msg==null || "null".equalsIgnoreCase(msg)){
                throw new RuntimeException("consuming null case error");
            }
            System.out.println("Consuming message: " + record.value());
            successCount++;
            showMessagesCount();
        } catch (Exception e) {
            errorCount++;
            System.err.println("Error Consuming message: " + e.getMessage());
            showMessagesCount();
        }
    }

    public void showMessagesCount(){
        System.out.println("Total success="+getSuccessCount()+" fail="+getErrorCount());
    }

}

