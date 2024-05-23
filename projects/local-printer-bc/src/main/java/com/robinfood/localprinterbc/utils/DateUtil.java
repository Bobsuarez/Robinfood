package com.robinfood.localprinterbc.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.FORMAT_DATE_WITH_HOUR_AND_PM_AM;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.FORMAT_DATE_WITH_LOCAL_TIME;

@Slf4j
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
            log.error("Error al parsear la fecha y hora:" + e.getMessage());
        }
        return LocalDateTime.parse(dateTimeString);
    }
}
