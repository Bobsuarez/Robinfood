package com.robinfood.configurations.models;

public class CityMock {

    public static City build() {

        return City.builder()
                .name("SanPaulo")
                .state(StateMock.build())
                .timezone("timezone")
                .build();
    }
}
