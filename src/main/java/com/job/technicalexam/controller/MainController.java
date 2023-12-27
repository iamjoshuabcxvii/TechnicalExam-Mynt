package com.job.technicalexam.controller;


import com.job.technicalexam.model.BaseResponse;
import com.job.technicalexam.config.exception.ErrorException;
import com.job.technicalexam.model.RequestModel;
import com.job.technicalexam.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/shipping")
public class MainController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping("/calculate")
    public ResponseEntity<BaseResponse> computeShippingCost(@RequestBody RequestModel requestModel) throws ErrorException, IOException {
        return new ResponseEntity<>(deliveryService.calculate(requestModel), HttpStatus.OK);
    }
}
