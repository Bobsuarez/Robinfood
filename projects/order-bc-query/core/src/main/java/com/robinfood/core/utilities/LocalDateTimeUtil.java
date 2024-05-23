package com.robinfood.core.utilities;

import static com.robinfood.core.constants.DateTimeConstants.START_HOUR;
import static com.robinfood.core.constants.DateTimeConstants.START_MINUTE;
import static com.robinfood.core.constants.DateTimeConstants.START_SECOND;
import static com.robinfood.core.constants.DateTimeConstants.LAST_HOUR;
import static com.robinfood.core.constants.DateTimeConstants.LAST_MINUTE;
import static com.robinfood.core.constants.DateTimeConstants.LAST_SECOND;
import static com.robinfood.core.constants.GlobalConstants.DAYS_OF_THE_WEEK;

import java.time.LocalDateTime;

public class LocalDateTimeUtil {

    private final LocalDateTime localDateTime;

    public LocalDateTimeUtil(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime startOfDay() {
        return LocalDateTime.of(
                localDateTime.getYear(),
                localDateTime.getMonth(),
                localDateTime.getDayOfMonth(),
                START_HOUR,
                START_MINUTE,
                START_SECOND
        );
    }

    public LocalDateTime lastOfDay() {
        return LocalDateTime.of(
                localDateTime.getYear(),
                localDateTime.getMonth(),
                localDateTime.getDayOfMonth(),
                LAST_HOUR,
                LAST_MINUTE,
                LAST_SECOND
        );
    }

    public LocalDateTime lastOfWeekSameHour() {
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
