package org.mashup.takoyaki.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {

    @Test
    public void localDateTimeTest() {
        String dateFormat = "yyyy-MM-dd hh:MM:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        LocalDateTime standardTime = LocalDateTime.of(2018, 8, 11, 11, 10, 20);
        String dateTime = standardTime.format(formatter);

        Assert.assertEquals("2018-08-11 11:08:20", dateTime);
    }

}