package com.job.technicalexam.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public final class DateTimeUtil {

    public String getCurrentDate() {
        // create a SimpleDateFormat object with the desired pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // get the current date using the Date class
        Date date = new Date();
        // format the date using the SimpleDateFormat object
        String formattedDate = dateFormat.format(date);
        // print the formatted date
        System.out.println(formattedDate);
        return formattedDate;
    }

    public Date convertStringToDate(String date) throws ParseException {
        // create a SimpleDateFormat object with the desired pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // parse two dates from strings using the SimpleDateFormat object
        Date date1 = dateFormat.parse(date);

        return date1;
    }

    public boolean isExpired(String currentDate, String expiryDate) throws ParseException {
        boolean same = currentDate.equals(expiryDate);
        boolean expired = convertStringToDate(expiryDate).before(convertStringToDate(currentDate));
        boolean result = same || expired;
        return result;
    }


}
