package com.bootcamp.apigateway.producer;

import com.bootcamp.apigateway.model.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogProducer {

    private final KafkaTemplate<String, Log> kafkaTemplate;

    public void sendLog(String topic, Log log) {
        kafkaTemplate.send(topic, log);
    }
}