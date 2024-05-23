package com.robinfood.localserver.commons.utilities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private DateTimeUtils(){

    }

    /***
     * Gets the current time taking the timezone into account and adapts it to a set format
     * @param timeZone timeZone of country example America/Bogota
     * @param format format date
     * @return current date and time
     */
    public static String getCurrentDateTimeInTimeZone(String timeZone, String format) {
        ZoneId zoneId = ZoneId.of(timeZone);
        ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(currentDateTime);
    }

    /***
     * Calculate the number of minutes between two dates
     * @param startDateTime date and time initial
     * @param endDateTime date and time final
     * @return number of minutes
     */
    public static long getMinutesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration duration = Duration.between(startDateTime, endDateTime);
        return duration.toMinutes();
    }

}
