package com.job.technicalexam;

import com.job.technicalexam.util.DateTimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DateTimeUtililityTest {

    @InjectMocks
    DateTimeUtil dateTimeUtil;

    @Test
    public void testNotYetExpiredDate() throws ParseException {
        String currentDate = "2023-12-27";
        String expiryDate = "2024-06-06";

        assertFalse(dateTimeUtil.isExpired(currentDate, expiryDate));
    }

    @Test
    public void testExpiredDate() throws ParseException {
        String currentDate = "2023-12-27";
        String expiryDate = "2023-12-19";

        assertTrue(dateTimeUtil.isExpired(currentDate, expiryDate));
    }
}
