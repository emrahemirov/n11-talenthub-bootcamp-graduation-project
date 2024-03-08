package com.bootcamp.reviewservice.advice.responseexceptionhandler;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.shared.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        String message = ex.getMessage();
        String description = request.getDescription(false);

        var restResponse = RestResponse.error(new ErrorResponse(message, description));

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleItemNotFoundExceptions(ItemNotFoundException ex, WebRequest request) {

        String message = ex.getBaseErrorMessage().getMessage();
        String description = request.getDescription(false);


        var restResponse = RestResponse.error(new ErrorResponse(message, description));

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }


}