package com.alex.interview.sodamachine.entity;

import org.springframework.http.HttpStatus;

public class Message {

    private String details;
    private HttpStatus status;

    public Message(String details){
        this.details = details;
    }

    public Message(String details, HttpStatus status){
        this.details = details;
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
