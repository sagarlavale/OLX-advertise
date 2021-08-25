package com.zensar.advertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String message;

    public InvalidTokenException() {
        this.message = "";
    }

    public InvalidTokenException(String error)
    {
        this.message = error;
    }

    @Override
    public String toString() {
        return "Invalid Token : "+ this.message;
    }
}
