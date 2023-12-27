package com.job.technicalexam.controller;


import com.job.technicalexam.model.BaseResponse;
import com.job.technicalexam.model.ErrorResponse;
import com.job.technicalexam.model.RequestModel;
import com.job.technicalexam.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class MainController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping("/calculate")
    public ResponseEntity<BaseResponse> computeShippingCost(@RequestBody RequestModel requestModel) throws ErrorResponse {
        BaseResponse baseResponse = null;


        baseResponse = deliveryService.calculate(requestModel);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
