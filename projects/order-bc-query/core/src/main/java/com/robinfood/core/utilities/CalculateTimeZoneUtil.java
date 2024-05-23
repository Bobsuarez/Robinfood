package com.robinfood.core.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class CalculateTimeZoneUtil {

    private final String timezone;

    public CalculateTimeZoneUtil(String timezone) {
        this.timezone = timezone;
    }

    private ZonedDateTime nowZoneTimeZone() {
        Instant nowUtc = Instant.now();
        ZoneId getZoneIdActual = ZoneId.of(this.timezone);
        return ZonedDateTime.ofInstant(nowUtc, getZoneIdActual);
    }

    public LocalDate getLocalDate() {
        return LocalDate.of(
                this.nowZoneTimeZone().getYear(),
                this.nowZoneTimeZone().getMonth(),
                this.nowZoneTimeZone().getDayOfMonth()
        );
    }

    public LocalTime getTimeLocal() {
        return LocalTime.of(
                this.nowZoneTimeZone().getHour(),
                this.nowZoneTimeZone().getMinute(),
                this.nowZoneTimeZone().getSecond()
        );
    }
}
