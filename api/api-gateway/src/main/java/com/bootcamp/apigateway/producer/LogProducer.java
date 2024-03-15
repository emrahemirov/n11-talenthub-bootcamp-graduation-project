package com.bootcamp.apigateway.producer;

import com.bootcamp.apigateway.dto.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogProducer {

    private final RabbitTemplate template;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing-key.name}")
    private String routingKeyName;

    public void sendLog(Log log) {
        template.convertAndSend(exchangeName, routingKeyName, log);
    }
}