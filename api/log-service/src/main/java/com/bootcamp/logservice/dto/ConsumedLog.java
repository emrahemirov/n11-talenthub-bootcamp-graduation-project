package com.bootcamp.logservice.dto;

public record ConsumedLog(
        String statusCode,
        String method,
        String reqUri,
        String reqBody,
        String resBody) {
}
