package com.robinfood.configurations.dto.v1;

import java.time.LocalDateTime;

public class StoreResolutionDTOMock {

    public static StoreResolutionDTO build() {

        LocalDateTime localDateTimeNow = LocalDateTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(
                localDateTimeNow.getYear(),
                localDateTimeNow.getMonth(),
                localDateTimeNow.getDayOfMonth(),
                localDateTimeNow.getHour(),
                localDateTimeNow.getMinute(),
                localDateTimeNow.getSecond()
        );

        return StoreResolutionDTO.builder()
                .confirmed(false)
                .document("test")
                .endDate(localDateTime.plusDays(1))
                .finalNumber(5)
                .invoiceNumber("test")
                .invoiceText("test")
                .name("test")
                .posId(1L)
                .prefix("test")
                .startDate(localDateTime)
                .serial("test")
                .startingNumber(1)
                .status(false)
                .build();
    }
}
