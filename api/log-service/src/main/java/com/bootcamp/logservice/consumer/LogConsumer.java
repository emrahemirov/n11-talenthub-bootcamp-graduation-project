package com.bootcamp.logservice.consumer;


import com.bootcamp.logservice.model.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogConsumer {

//    private final LogRepository repository;


    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeLog(Log log) {
        System.out.println(log.toString());
    }
}