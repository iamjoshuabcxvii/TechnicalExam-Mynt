package com.job.technicalexam.service;

import com.job.technicalexam.model.DatabaseModel;
import com.job.technicalexam.model.ErrorResponse;
import com.job.technicalexam.model.RequestModel;
import com.job.technicalexam.model.Volume;
import com.job.technicalexam.repository.DatabaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.job.technicalexam.enums.RulesEnum.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceTest {
    final double delta = 0.0001;
    @InjectMocks
    DeliveryService deliveryService;

    @Mock
    DatabaseRepository databaseRepository;

    @Test
    public void testRejectedParcelFlow() throws ErrorResponse {
        when(databaseRepository.findAll()).thenReturn(mockDatabaseContents());
        assertEquals(0, deliveryService.calculate(rejectParcelMockData()).getTotalAmount(), delta);
    }

    @Test
    public void testHeavyParcelFlow() throws ErrorResponse {
        when(databaseRepository.findAll()).thenReturn(mockDatabaseContents());
        assertEquals(800, deliveryService.calculate(heavyParcelMockData()).getTotalAmount(), delta);

    }

    @Test
    public void testSmallParcelFlow() throws ErrorResponse {
        when(databaseRepository.findAll()).thenReturn(mockDatabaseContents());
        assertEquals(45.0, deliveryService.calculate(smallParcelMockData()).getTotalAmount(), delta);

    }

    @Test
    public void testMediumParcelFlow() throws ErrorResponse {
        when(databaseRepository.findAll()).thenReturn(mockDatabaseContents());
        assertEquals(80.0, deliveryService.calculate(mediumParcelMockData()).getTotalAmount(), delta);

    }

    @Test
    public void testLargeParcelFlow() throws ErrorResponse {
        when(databaseRepository.findAll()).thenReturn(mockDatabaseContents());
        assertEquals(150.0, deliveryService.calculate(largeParcelMockData()).getTotalAmount(), delta);

    }

    private RequestModel rejectParcelMockData() {
        RequestModel requestModel = new RequestModel();
        requestModel.setWeight(55.0);
        requestModel.setVoucherCode("MYNT");
        return requestModel;
    }

    private RequestModel heavyParcelMockData() {
        RequestModel requestModel = new RequestModel();
        requestModel.setWeight(40.0);
        requestModel.setVoucherCode("MYNT");
        return requestModel;
    }


    private RequestModel smallParcelMockData() {
        RequestModel requestModel = new RequestModel();
        Volume volume = new Volume();
        volume.setHeight(10.0);
        volume.setWidth(10.0);
        volume.setLength(15.0);
        requestModel.setVolume(volume);
        requestModel.setVoucherCode("MYNT");

        return requestModel;
    }

    private RequestModel mediumParcelMockData() {
        RequestModel requestModel = new RequestModel();
        Volume volume = new Volume();
        volume.setHeight(10.0);
        volume.setWidth(10.0);
        volume.setLength(20.0);
        requestModel.setVolume(volume);
        requestModel.setVoucherCode("MYNT");

        return requestModel;
    }

    private RequestModel largeParcelMockData() {
        RequestModel requestModel = new RequestModel();
        Volume volume = new Volume();
        volume.setHeight(10.0);
        volume.setWidth(10.0);
        volume.setLength(30.0);
        requestModel.setVolume(volume);
        requestModel.setVoucherCode("MYNT");

        return requestModel;
    }


    private List<DatabaseModel> mockDatabaseContents() {
        DatabaseModel databaseModelOne = new DatabaseModel();
        DatabaseModel databaseModelTwo = new DatabaseModel();
        DatabaseModel databaseModelThree = new DatabaseModel();
        DatabaseModel databaseModelFour = new DatabaseModel();
        DatabaseModel databaseModelFive = new DatabaseModel();

        databaseModelOne.setRuleName(REJECT.name());
        databaseModelOne.setPriority(1);
        databaseModelOne.setMinWeight(50.0);

        databaseModelTwo.setRuleName(HEAVY_PARCEL.name());
        databaseModelTwo.setPriority(2);
        databaseModelTwo.setMinWeight(10.0);
        databaseModelTwo.setMaxWeight(50.0);
        databaseModelTwo.setPrice(20.0);

        databaseModelThree.setRuleName(LARGE_PARCEL.name());
        databaseModelThree.setPriority(5);
        databaseModelThree.setMinWeight(0.0);
        databaseModelThree.setMaxWeight(10.0);
        databaseModelThree.setMinVolume(2500.0);
        databaseModelThree.setPrice(0.05);

        databaseModelFour.setRuleName(MEDIUM_PARCEL.name());
        databaseModelFour.setPriority(4);
        databaseModelFour.setMinVolume(1500.0);
        databaseModelFour.setMaxVolume(2500.0);
        databaseModelFour.setPrice(0.04);

        databaseModelFive.setRuleName(SMALL_PARCEL.name());
        databaseModelFive.setPriority(3);
        databaseModelFive.setMinVolume(0.0);
        databaseModelFive.setMaxVolume(1500.0);
        databaseModelFive.setPrice(0.03);

        List<DatabaseModel> databaseModelList = new ArrayList<>();

        databaseModelList.add(databaseModelOne);
        databaseModelList.add(databaseModelTwo);
        databaseModelList.add(databaseModelThree);
        databaseModelList.add(databaseModelFour);
        databaseModelList.add(databaseModelFive);

        return databaseModelList;
    }
}
