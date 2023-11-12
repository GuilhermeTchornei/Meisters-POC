package com.meisters.api.Errors;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
