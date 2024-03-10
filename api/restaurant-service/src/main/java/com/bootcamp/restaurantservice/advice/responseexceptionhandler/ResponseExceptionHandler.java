package com.bootcamp.restaurantservice.advice.responseexceptionhandler;

import com.bootcamp.restaurantservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.restaurantservice.shared.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        String message = ex.getMessage();


        return new ResponseEntity<>(RestResponse.error(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public final ResponseEntity<Object> handleItemNotFoundExceptions(ItemNotFoundException ex, WebRequest request) {

        String message = ex.getMessage();


        return new ResponseEntity<>(RestResponse.error(message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(RestResponse.error(errors), HttpStatus.BAD_REQUEST);
    }


}