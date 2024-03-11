package com.bootcamp.reviewservice.advice.responseexceptionhandler;


import lombok.Getter;

@Getter
public enum ErrorMessage {
    ITEM_NOT_FOUND("item not found"),
    REVIEW_NOT_FOUND("review not found"),
    USER_NOT_FOUND("user not found"),
    USER_ADDRESS_NOT_FOUND("user address not found"),
    RESTAURANT_NOT_FOUND("restaurant not found");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }


}