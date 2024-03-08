package com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.BaseErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BaseException extends RuntimeException {

    private final BaseErrorMessage baseErrorMessage;


}