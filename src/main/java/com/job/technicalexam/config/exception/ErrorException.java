package com.job.technicalexam.config.exception;

import lombok.Data;

@Data
public class ErrorException extends Exception{
    private String error;

    public ErrorException(String message) {
        super(message);
        this.error = message;
    }
}
