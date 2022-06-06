package com.delivery.deliveryapi.exception;

import com.delivery.deliveryapi.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        ResponseDto restApiException = new ResponseDto();
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setMessage(ex.getMessage());

        return new ResponseEntity<>(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }
}