package com.job.technicalexam.service;

import com.job.technicalexam.model.BaseResponse;
import com.job.technicalexam.model.DatabaseModel;
import com.job.technicalexam.model.ErrorResponse;
import com.job.technicalexam.model.RequestModel;
import com.job.technicalexam.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.job.technicalexam.enums.RulesEnum.REJECT;

@Service
public class DeliveryService {

    @Autowired
    DatabaseRepository databaseRepository;

    @Autowired
    VoucherService voucherService;

    public BaseResponse calculate(RequestModel requestModel) throws ErrorResponse {
        boolean hasDiscount = Optional.ofNullable(requestModel.getVoucherCode()).isPresent() ? true : false;
        BaseResponse baseResponse = new BaseResponse();
        double inputtedLength, inputtedWidth, inputtedHeight, computedVolume = 0;

        Optional<Object> volume = Optional.ofNullable(requestModel.getVolume());

        if (volume.isPresent()) {
            inputtedLength = requestModel.getVolume().getLength();
            inputtedWidth = requestModel.getVolume().getWidth();
            inputtedHeight = requestModel.getVolume().getHeight();
            computedVolume = inputtedLength * inputtedWidth * inputtedHeight;
        }


        List<DatabaseModel> databaseConfig;

        databaseConfig = databaseRepository.findAll();
        Optional<Double> inputtedWeight = Optional.ofNullable(Optional.ofNullable(requestModel.getWeight()).isPresent() ? requestModel.getWeight() : null);


        if (inputtedWeight.isPresent() && !volume.isPresent()) {
            DatabaseModel databaseModelOne, databaseModelTwo;

            databaseModelOne = databaseConfig.stream().filter(record -> record.getPriority() == 1).collect(Collectors.toList()).get(0);
            databaseModelTwo = databaseConfig.stream().filter(record -> record.getPriority() == 2).collect(Collectors.toList()).get(0);
            if (inputtedWeight.get() >= databaseModelOne.getMinWeight()) {
                // REJECT FLow

                throw new ErrorResponse(HttpStatus.OK, REJECT.name());
            } else if (inputtedWeight.get() >= databaseModelTwo.getMinWeight() && inputtedWeight.get() <= databaseModelTwo.getMaxWeight()) {
                //Heavy Flow
                baseResponse.setTotalAmount(computeForPrice(inputtedWeight.get(), databaseModelTwo.getPrice(), hasDiscount, requestModel));

            } else {
                //Unhandled Flow
            }
        } else if (!inputtedWeight.isPresent() && volume.isPresent()) {
            // Small, Medium & Large Flow
            DatabaseModel databaseModelThree, databaseModelFour, databaseModelFive;

            databaseModelThree = databaseConfig.stream().filter(record -> record.getPriority() == 3).collect(Collectors.toList()).get(0);
            databaseModelFour = databaseConfig.stream().filter(record -> record.getPriority() == 4).collect(Collectors.toList()).get(0);
            databaseModelFive = databaseConfig.stream().filter(record -> record.getPriority() == 5).collect(Collectors.toList()).get(0);

            if (computedVolume >= databaseModelThree.getMinVolume() && computedVolume <= databaseModelThree.getMaxVolume()) {
                // Small Flow
                baseResponse.setTotalAmount(computeForPrice(computedVolume, databaseModelThree.getPrice(), hasDiscount, requestModel));
            } else if (computedVolume >= databaseModelFour.getMinVolume() && computedVolume <= databaseModelFour.getMaxVolume()) {
                // Medium Flow
                baseResponse.setTotalAmount(computeForPrice(computedVolume, databaseModelFour.getPrice(), hasDiscount, requestModel));
            } else {
                // Large Flow
                baseResponse.setTotalAmount(computeForPrice(computedVolume, databaseModelFive.getPrice(), hasDiscount, requestModel));
            }
        } else {
            // Unhandled flow
        }
        return baseResponse;
    }

    private double computeForPrice(final double weightOrVolume, double price, boolean hasDiscount, RequestModel requestModel) {
        double totalAmount = 0;
        if(hasDiscount) {
            totalAmount = voucherService.voucherApiCall(requestModel.getVoucherCode(), price, weightOrVolume);
        } else {
            totalAmount = price * weightOrVolume;
        }
        return totalAmount;
    }
}
