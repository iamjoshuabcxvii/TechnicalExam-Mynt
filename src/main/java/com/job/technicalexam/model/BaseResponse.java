package com.job.technicalexam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@Data
public class BaseResponse {

    private double totalAmount;
//    private double discountApplied;
//    private double discountedAmount;
}
