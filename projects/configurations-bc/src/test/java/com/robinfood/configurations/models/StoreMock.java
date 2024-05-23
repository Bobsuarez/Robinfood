package com.robinfood.configurations.models;

public class StoreMock {

    public static Store build() {

        return Store.builder()
                .city(CityMock.build())
                .company(Company.builder().build())
                .name("Calle 80")
                .storeIdentifierType(StoreIdentifierType.builder().build())
                .build();
    }
}
