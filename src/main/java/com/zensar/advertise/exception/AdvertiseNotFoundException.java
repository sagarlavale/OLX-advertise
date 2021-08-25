package com.zensar.advertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class AdvertiseNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer id;

    public AdvertiseNotFoundException() {
        this.id = null;
    }

    public AdvertiseNotFoundException(Integer error)
    {
        this.id = error;
    }

    @Override
    public String toString() {
        return "Invalid Advertise ID : "+ this.id;
    }
}
