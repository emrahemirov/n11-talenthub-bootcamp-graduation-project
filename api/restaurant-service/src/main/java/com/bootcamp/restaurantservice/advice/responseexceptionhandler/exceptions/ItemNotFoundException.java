package com.bootcamp.restaurantservice.advice.responseexceptionhandler.exceptions;

import com.bootcamp.restaurantservice.advice.responseexceptionhandler.ErrorMessage;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public ItemNotFoundException() {
        super(ErrorMessage.ITEM_NOT_FOUND.getMessage());
    }


}
