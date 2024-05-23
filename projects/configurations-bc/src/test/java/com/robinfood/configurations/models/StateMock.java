package com.robinfood.configurations.models;

public class StateMock {

    public static State build() {

        return State.builder()
                .country(CountryMock.build())
                .name("SanPaulo")
                .build();
    }
}
