package com.zensar.advertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class StatusNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    Integer id;

    public StatusNotFoundException() {
        this.id = null;
    }

    public StatusNotFoundException(Integer error)
    {
        this.id = error;
    }

    @Override
    public String toString() {
        return "Invalid Status ID : "+ this.id;
    }
}
