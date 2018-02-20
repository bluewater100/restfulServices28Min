package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

// This class was created by me and it is the format which all exceptions will use.
// In this way we have a consistent format for our exception responses
public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, String message, String details) {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
