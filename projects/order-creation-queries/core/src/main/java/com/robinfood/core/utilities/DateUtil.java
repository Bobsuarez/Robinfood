package com.robinfood.core.utilities;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    public static final String FORMAT  = "yyyy-MM-dd HH:mm:ss";

    public static final Long DAYS_OF_THE_WEEK = 7L;

    private DateUtil() {
        // this constructor is empty because it is a configuration class
    }

    public static String formatDate(LocalDateTime localDateTime) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern(FORMAT);

        return localDateTime.format(f);
    }

    public static LocalDateTime lastOfWeekSameHour(LocalDateTime localDateTime) {
        return LocalDateTime.of(
                localDateTime.getYear(),
                localDateTime.getMonth(),
                localDateTime.getDayOfMonth(),
                localDateTime.getHour(),
                localDateTime.getMinute(),
                localDateTime.getSecond()
        ).minusDays(DAYS_OF_THE_WEEK);
    }
}
