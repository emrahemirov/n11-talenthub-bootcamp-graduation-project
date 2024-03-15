package com.bootcamp.logservice.model;

import com.mongodb.BasicDBObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs")
public class Log {

    @Id
    private String id;
    private String statusCode;
    private String method;
    private String reqUri;
    private BasicDBObject reqBody;
    private BasicDBObject resBody;


    @Override
    public String toString() {
        return "ConsumedLog{" +
                "statusCode='" + statusCode + '\'' +
                ", method='" + method + '\'' +
                ", reqUri='" + reqUri + '\'' +
                ", reqBody='" + reqBody + '\'' +
                ", resBody='" + resBody + '\'' +
                '}';
    }


}


