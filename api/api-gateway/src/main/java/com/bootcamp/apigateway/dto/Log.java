package com.bootcamp.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    String statusCode;
    String method;
    String reqUri;
    String reqBody;
    String resBody;


    @Override
    public String toString() {
        return "Log{" +
                "statusCode='" + statusCode + '\'' +
                ", method='" + method + '\'' +
                ", reqUri='" + reqUri + '\'' +
                ", reqBody='" + reqBody + '\'' +
                ", resBody='" + resBody + '\'' +
                '}';
    }
}
