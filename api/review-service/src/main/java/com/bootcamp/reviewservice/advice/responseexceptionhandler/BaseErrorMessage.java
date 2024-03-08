package com.bootcamp.reviewservice.advice.responseexceptionhandler;

import java.io.Serializable;

public interface BaseErrorMessage extends Serializable {

    String getMessage();
}