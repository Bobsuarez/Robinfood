package com.robinfood.core.utilities;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;

@Slf4j
public final class CalculateDateDisplacementUTC {

    private CalculateDateDisplacementUTC() {}

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


}
