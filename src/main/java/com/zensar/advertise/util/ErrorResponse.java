package com.zensar.advertise.util;

import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
public class ErrorResponse {
    protected Integer code;

    protected String message;

    protected String summary;

    protected String stackTrace;

    private String status;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(Integer code, String message, Exception ex) {
        super();

        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));

        this.code = code;
        this.message = message;
        this.summary = ex.getMessage();
        this.stackTrace = writer.toString();
    }

    public ErrorResponse(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


}

