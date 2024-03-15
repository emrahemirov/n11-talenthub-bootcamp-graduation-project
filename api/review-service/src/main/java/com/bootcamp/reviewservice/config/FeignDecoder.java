package com.bootcamp.reviewservice.config;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;


public class FeignDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new ItemNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
