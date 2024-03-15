package com.bootcamp.logservice.consumer;


import com.bootcamp.logservice.dto.ConsumedLog;
import com.bootcamp.logservice.model.Log;
import com.bootcamp.logservice.repository.LogRepository;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogConsumer {

    private final LogRepository repository;


    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeLog(ConsumedLog consumedLog) {
        Gson gson = new Gson();
        Log log = new Log();

        BasicDBObject reqBody = gson.fromJson(consumedLog.reqBody(), BasicDBObject.class);
        BasicDBObject resBody = gson.fromJson(consumedLog.resBody(), BasicDBObject.class);

        log.setReqBody(reqBody);
        log.setResBody(resBody);
        log.setMethod(consumedLog.method());
        log.setReqUri(consumedLog.reqUri());
        log.setMethod(consumedLog.method());

        repository.save(log);
    }
}