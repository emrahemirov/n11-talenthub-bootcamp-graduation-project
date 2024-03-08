package com.bootcamp.reviewservice.advice.responseexceptionhandler;


public enum ErrorMessage implements BaseErrorMessage {
    ITEM_NOT_FOUND("item_not_found"),
    REVIEW_NOT_FOUND("review_not_found"),
    USER_NOT_FOUND("user_not_found"),
    USER_ADDRESS_NOT_FOUND("user_address_not_found");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


}