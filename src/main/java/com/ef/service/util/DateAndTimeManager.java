package com.ef.service.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Chaklader on Oct, 2017
 */
public class DateAndTimeManager {


    /*
    * Get the date in the local time from date
    * */
    public static LocalDateTime convertDateToLocalTime(Date date) {

        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    /*
    * Get the date in the local time from the date String
    * */
    public static LocalDateTime convertDateToLocalTime(String date) {

        if (date == null || date.isEmpty()) {
            return null;
        }

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalDateTime.now().toLocalTime());
        return localDateTime;
    }
}
