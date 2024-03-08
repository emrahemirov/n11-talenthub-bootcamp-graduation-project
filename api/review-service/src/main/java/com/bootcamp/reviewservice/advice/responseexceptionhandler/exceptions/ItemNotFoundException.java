package com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.BaseErrorMessage;
import com.bootcamp.reviewservice.advice.responseexceptionhandler.ErrorMessage;

public class ItemNotFoundException extends BaseException {

    public ItemNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

    public ItemNotFoundException() {
        super(ErrorMessage.ITEM_NOT_FOUND);
    }


}
