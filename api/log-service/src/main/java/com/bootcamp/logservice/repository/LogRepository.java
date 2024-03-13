package com.bootcamp.logservice.repository;

import com.bootcamp.logservice.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}
