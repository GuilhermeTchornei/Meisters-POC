package com.meisters.api.Errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
