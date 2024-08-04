package com.micro.hotel.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handlerResourceNotFoundException(ResourceNotFoundException exception){

        Map<String,Object> mapData=new HashMap<>();
        mapData.put("message", exception.getMessage());
        mapData.put("success",false);
        mapData.put("status", HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapData);
    }



}
