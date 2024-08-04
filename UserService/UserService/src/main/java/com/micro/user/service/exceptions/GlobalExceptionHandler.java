package com.micro.user.service.exceptions;

import com.micro.user.service.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handlerResourceNotFoundException(ResourceNotFoundException exception){

        APIResponse response=APIResponse.builder().message(exception.getMessage()).success(true).status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<APIResponse>(response,HttpStatus.NOT_FOUND);
    }



}
