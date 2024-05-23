package com.robinfood.paymentmethodsbc.mappers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class UtilMapper {

    private UtilMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static String toIsoFormat(LocalDateTime dateTime) {
        return dateTime
            .atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
