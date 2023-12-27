package com.job.technicalexam.config.exception;

import lombok.Data;

@Data
public class ErrorHandlerResponse {
    private String error;

    public ErrorHandlerResponse(String error) {
        this.error = error;
    }
}
