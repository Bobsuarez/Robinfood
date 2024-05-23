package com.robinfood.core.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    
    private LocalDateTime localDateTime;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public DateHelper(LocalDateTime dateTime) {
        localDateTime = dateTime;
    }

    public String getDateString(){
        return localDateTime.format(dateFormatter);
    }

    public String getTimeString(){
        return localDateTime.format(timeFormatter);
    }

    public String getDateTimeString(){
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime getLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
    }

    public static LocalDate getLocalDate(String dateString) {
        return LocalDate.parse(dateString, dateFormatter);
    }

    public static LocalTime getLocalTime(String timeString) {
        return LocalTime.parse(timeString, timeFormatter);
    }
}
