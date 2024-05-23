package com.robinfood.core.utilities;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;

@Slf4j
public final class CalculateDateDisplacementUTCUtil {

    private CalculateDateDisplacementUTCUtil() {}

    public static ZoneId getZoneId(String timeZone) {

        log.info("Get Zone Id timeZone {}", timeZone);

        return ZoneId.of(timeZone);
    }

    public static LocalDate getLocalDate(ZoneId zoneId){

        log.info("Get Local Date Zone Id {}", zoneId);

        return LocalDate.now(zoneId);
    }

    public static LocalDateTime getLocalDateTimeStart(LocalDate localDate, ZoneId zoneId) {

        log.info("Get Local Date Time Start localDate {}, zoneId {}", localDate, zoneId);

        Instant instantStart = localDate.atStartOfDay(zoneId).toInstant();

        return LocalDateTime.ofInstant(instantStart, ZoneOffset.UTC).plusSeconds(1L);
    }

    public static LocalDateTime getLocalDateTimeEnd(LocalDate localDate, ZoneId zoneId) {

        log.info("Get Local Date Time End localDate {}, zoneId {}", localDate, zoneId);

        Instant instantEnd = localDate.plusDays(DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT).atStartOfDay(zoneId).toInstant();

        return LocalDateTime.ofInstant(instantEnd, ZoneOffset.UTC).plusSeconds(1L);
    }

    public static Map<String, LocalDateTime> getLocalDateTime(String timeZone) {

        final ZoneId zoneId = getZoneId(timeZone);
        final LocalDate localDate = getLocalDate(zoneId);

        Map<String, LocalDateTime> localDateHours = new HashMap<>();

        localDateHours.put(LOCAL_DATE_TIME_END, getLocalDateTimeEnd(localDate, zoneId));
        localDateHours.put(LOCAL_DATE_TIME_START, getLocalDateTimeStart(localDate, zoneId));

        return localDateHours;
    }

    public static Map<String, LocalDateTime> getLocalDateTimeByRange(
            LocalDate localDateTimeEnd,
            LocalDate localDateTimeStart,
            String timeZone
    ) {

        final ZoneId zoneId = getZoneId(timeZone);

        Map<String, LocalDateTime> localDateHours = new HashMap<>();

        localDateHours.put(LOCAL_DATE_TIME_END, getLocalDateTimeEnd(localDateTimeEnd, zoneId));
        localDateHours.put(LOCAL_DATE_TIME_START, getLocalDateTimeStart(localDateTimeStart, zoneId));

        return localDateHours;
    }

    public static Map<String, LocalDateTime> getLocalDateByRange(
            LocalDate localDateEnd,
            LocalDate localDateStart,
            String timeZone
    ) {

        final ZoneId zoneId = getZoneId(timeZone);

        Map<String, LocalDateTime> localDateHours = new HashMap<>();

        localDateHours.put(LOCAL_DATE_END, getLocalDateTimeEnd(localDateEnd, zoneId));
        localDateHours.put(LOCAL_DATE_START, getLocalDateTimeStart(localDateStart, zoneId));

        return localDateHours;
    }

}
