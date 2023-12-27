package com.job.technicalexam.config;

import com.job.technicalexam.config.exception.ErrorException;
import com.job.technicalexam.config.exception.ErrorHandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorHandlerResponse> handleHttpClientErrorException(ErrorException ex) {
        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse(ex.getError());
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (errorHandlerResponse);
    }

}
