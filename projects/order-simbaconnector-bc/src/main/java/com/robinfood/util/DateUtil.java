package com.robinfood.util;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.robinfood.constants.GeneralConstants.FORMAT_DATE_WITH_HOUR_AND_PM_AM;
import static com.robinfood.constants.GeneralConstants.FORMAT_DATE_WITH_LOCAL_TIME;

public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatStandard(LocalDateTime date) {

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(FORMAT_DATE_WITH_HOUR_AND_PM_AM);

        return date.format(formatDate);
    }

    public static LocalDateTime parseFull(String dateTimeString) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_WITH_LOCAL_TIME);

            return LocalDateTime.parse(dateTimeString, formatter);

        } catch (DateTimeParseException e) {
            LogsUtil.info("Error al parsear la fecha y hora:" + e.getMessage());
        }
        return LocalDateTime.parse(dateTimeString);
    }

    public static String parseDateAndHourNow(ZonedDateTime dateNow) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");

        return dateNow.format(formatter);
    }
}
