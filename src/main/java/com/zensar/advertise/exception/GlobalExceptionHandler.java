package com.zensar.advertise.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AdvertiseNotFoundException.class})
    public ResponseEntity handleAdvertiseNotException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }

    @ExceptionHandler(value = {CategoryNotFoundException.class})
    public ResponseEntity handleCategoryNotFoundException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }

    @ExceptionHandler(value = {StatusNotFoundException.class})
    public ResponseEntity handleStatusNotFoundException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }

    @ExceptionHandler(value = {InvalidTokenException.class})
    public ResponseEntity handleInvalidTokenException(RuntimeException runtimeException, WebRequest webRequest)
    {
        return handleExceptionInternal(runtimeException,runtimeException.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }
}
