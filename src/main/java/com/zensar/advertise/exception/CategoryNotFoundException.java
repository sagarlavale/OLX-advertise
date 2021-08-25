package com.zensar.advertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class CategoryNotFoundException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Integer id;

    public CategoryNotFoundException() {
        this.id = null;
    }

    public CategoryNotFoundException(Integer error)
    {
        this.id = error;
    }

    @Override
    public String toString() {
        return "Invalid Category ID : "+ this.id;
    }
}
