package com.bootcamp.apigateway.model;

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
    Object reqBody;
    Object resBody;
}
