package com.robinfood.configurations.models;

import java.time.LocalDateTime;

public class ResolutionMock {

    public static Resolution build() {

        return Resolution.builder()
                .document("test")
                .endDate(LocalDateTime.now())
                .finalNumber("5")
                .invoiceNumberResolutions("test")
                .invoiceText("test")
                .name("test")
                .posId(1L)
                .prefix("test")
                .serial("test")
                .startDate(LocalDateTime.now())
                .startingNumber("1")
                .status(1)
                .build();
    }
}
