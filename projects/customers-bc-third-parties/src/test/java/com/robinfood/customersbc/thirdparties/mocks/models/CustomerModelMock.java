package com.robinfood.customersbc.thirdparties.mocks.models;

import com.robinfood.customersbc.thirdparties.models.CustomerModel;

import java.time.LocalDateTime;

public final class CustomerModelMock {

    public CustomerModelMock() {}

    public static CustomerModel getCustomerModel() {
        return CustomerModel.builder()
            .id(1L)
            .externalId(22L)
            .firstName("Juan")
            .lastName("Perez")
            .email("juan@muy.com")
            .phoneCode("57")
            .phoneNumber("3008765544")
            .status(1)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
