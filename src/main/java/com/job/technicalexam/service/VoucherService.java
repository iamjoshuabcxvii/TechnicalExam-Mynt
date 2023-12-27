package com.job.technicalexam.service;

import com.job.technicalexam.model.VoucherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VoucherService {
    private final String domain;
    private final String path;

    public VoucherService(@Value("${domain}") String domain,
                          @Value("${voucher.path}") String path) {
        this.domain = domain;
        this.path = path;
    }

    public double voucherApiCall(final String voucherCode, double price, double weightOrVolume) {
        double totalAmount;
        // request url
        String url = domain + path + "/" + voucherCode;

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // make an HTTP GET request
        VoucherApiResponse voucherApiResponse = restTemplate.getForObject(url, VoucherApiResponse.class);

//        System.out.println("Response: " + json);
        totalAmount = (price * weightOrVolume) - ((price * weightOrVolume) * (Double.parseDouble(voucherApiResponse.getDiscount()) / 100));

        return totalAmount;
    }
}
