package com.robinfood.configurations.models;

public class CountryMock {

    public static Country build() {

        return Country.builder()
                .name("Brazil")
                .build();
    }
}
