package com.robinfood.core.utilities;

import static com.robinfood.core.constants.DateTimeConstants.LAST_HOUR;
import static com.robinfood.core.constants.DateTimeConstants.LAST_MINUTE;
import static com.robinfood.core.constants.DateTimeConstants.LAST_SECOND;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Slf4j
public class KeyAnyWithTimezonesUtil {

    private final List<Integer> keys;
    private final List<String> timezones;

    public KeyAnyWithTimezonesUtil(
            List<Integer> keys,
            List<String> timezones
    ) {
        this.keys = keys;
        this.timezones = timezones;
    }

    public Map<Integer, ZoneId> withZoneId() {

        final ListIterator<Integer> companiesIterator = this.keys.listIterator();
        HashMap<Integer, ZoneId> keyIterator = new HashMap<>();

        while (companiesIterator.hasNext()) {
            int index = companiesIterator.nextIndex();
            Integer company = companiesIterator.next();
            keyIterator.put(company, ZoneId.of(timezones.get(index)));
        }

        log.info("(KeyAnyWithTimezonesUtil) -> (withZoneId) Response with UTC: {}", keyIterator);

        return keyIterator;
    }

    public Map<Integer, LocalDateTime> withLocalTimeByZoneIds(
            LocalDateTime localDateTimeUTC,
            Map<Integer, ZoneId> zoneIds
    ) {

        Map<Integer, LocalDateTime> keysWithDateLocalTimes = new HashMap<>();

        final ZonedDateTime timeInUtc = localDateTimeUTC.atZone(ZoneOffset.UTC);

        zoneIds.forEach((companyId, zoneId) -> {

            final ZonedDateTime zonedDateTimeLocal = timeInUtc.withZoneSameInstant(zoneId);
            final LocalDateTime localDateTimeLocal = LocalDateTime.of(
                    zonedDateTimeLocal.getYear(), zonedDateTimeLocal.getMonth(), zonedDateTimeLocal.getDayOfMonth(),
                    zonedDateTimeLocal.getHour(), zonedDateTimeLocal.getMinute(), zonedDateTimeLocal.getSecond()
            );

            keysWithDateLocalTimes.put(
                    companyId,
                    localDateTimeLocal
            );
        });

        final Map<Integer, LocalDateTime> timeZoneLimit = timeZoneLimit(keysWithDateLocalTimes);

        log.info("(KeyAnyWithTimezonesUtil) -> (withLocalTimeByZoneIds) Response with UTC: {}", timeZoneLimit);

        return timeZoneLimit;
    }

    private Map<Integer, LocalDateTime> timeZoneLimit(
            Map<Integer, LocalDateTime> keysWithLocalTimes
    ) {
        Map<Integer, LocalDateTime> getKeysWithLocalTimes = new HashMap<>();

        final LocalDateTime localDateTimeFirst = keysWithLocalTimes.get(keys.get(DEFAULT_INTEGER_VALUE));

        keysWithLocalTimes.forEach((key, localTime) -> {
            if (
                    localTime.getDayOfMonth() != localDateTimeFirst.getDayOfMonth() &&
                            localTime.isAfter(localDateTimeFirst)
            ) {
                getKeysWithLocalTimes.put(
                        key,
                        LocalDateTime.of(
                                localDateTimeFirst.getYear(),
                                localDateTimeFirst.getMonth(),
                                localDateTimeFirst.getDayOfMonth(),
                                LAST_HOUR, LAST_MINUTE, LAST_SECOND
                        )
                );
            } else {
                getKeysWithLocalTimes.put(
                        key,
                        localTime
                );
            }
        });

        return getKeysWithLocalTimes;
    }
}