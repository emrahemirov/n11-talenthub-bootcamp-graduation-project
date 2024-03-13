package com.bootcamp.logservice.consumer;


import com.bootcamp.logservice.model.Log;
import com.bootcamp.logservice.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogConsumer {

    private final LogRepository repository;

    @KafkaListener(topics = "log", groupId = "log-consumer-group")
    public void consume(String message) {

        Log errorLog = new Log();
        errorLog.setStatusCode("400");

        repository.save(errorLog);
    }
}