package com.job.technicalexam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.technicalexam.config.exception.ErrorException;
import com.job.technicalexam.model.VoucherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class VoucherService {
    private final String domain;
    private final String path;

    public VoucherService(@Value("${domain}") String domain,
                          @Value("${voucher.path}") String path) {
        this.domain = domain;
        this.path = path;
    }

    public double voucherApiCall(final String voucherCode, double price, double weightOrVolume) throws IOException, ErrorException {
        double totalAmount;
        String url = domain + path + "/" + voucherCode;

        String response = null;
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        VoucherApiResponse voucherApiResponse = null;
        ErrorException errorException =  null;
        try {
            response = restTemplate.getForObject(url, String.class);
            voucherApiResponse = mapper.readValue(response, VoucherApiResponse.class);
        } catch (HttpClientErrorException exception) {
            errorException = mapper.readValue(exception.getResponseBodyAsString(), ErrorException.class);

            throw new ErrorException(errorException.getError());
        }

        totalAmount = (price * weightOrVolume) - ((price * weightOrVolume) * (Double.parseDouble(voucherApiResponse.getDiscount()) / 100));

        return totalAmount;
    }
}
