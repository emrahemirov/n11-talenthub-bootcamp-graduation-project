package com.bootcamp.restaurantservice.advice.responseexceptionhandler;


import lombok.Getter;

@Getter
public enum ErrorMessage {
    ITEM_NOT_FOUND("item not found"),
    RESTAURANT_NOT_FOUND("restaurant not found"),
    PREFERRED_USER_ADDRESS_NOT_FOUND("preferred user address not found");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }


}