package com.job.technicalexam.model;

import lombok.Data;

@Data
public class VoucherApiResponse {

    private String code;
    private String discount;
    private String expiry;
}
