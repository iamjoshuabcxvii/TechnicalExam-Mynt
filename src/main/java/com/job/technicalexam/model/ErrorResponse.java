package com.job.technicalexam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse extends Throwable{
    private HttpStatus status;
//    private String message;
    private String reason;
}
