package com.micro.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {

        super("RESOURCE NOT FOUND HERE...!!!");

    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
