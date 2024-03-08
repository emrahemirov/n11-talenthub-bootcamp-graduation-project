package com.bootcamp.reviewservice.advice.responseexceptionhandler;

public record ErrorResponse(
        String message,
        String description) {

}
