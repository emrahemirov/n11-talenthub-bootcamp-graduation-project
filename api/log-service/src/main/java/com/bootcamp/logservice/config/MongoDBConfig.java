package com.bootcamp.logservice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = "com.bootcamp.logservice")
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Value("${mongodb.connection-uri}")
    private String connectionUri;


    @Override
    protected String getDatabaseName() {
        return "logs";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(connectionUri));
    }
}