package com.bootcamp.restaurantservice.modules.restaurant.config.feign;

import com.bootcamp.restaurantservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.restaurantservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;


public class FeignDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new ItemNotFoundException(ErrorMessage.PREFERRED_USER_ADDRESS_NOT_FOUND);
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
