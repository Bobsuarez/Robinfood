package com.robinfood.customersbc.thirdparties.mocks.models;

import com.robinfood.customersbc.thirdparties.models.ThirdPartyModel;

import java.time.LocalDateTime;

public final class ThirdPartyModelMock {

    private ThirdPartyModelMock() {}

    public static ThirdPartyModel getThirdPartyModel() {
        return ThirdPartyModel.builder()
            .id(1L)
            .customerId(1L)
            .identificationTypeId(1)
            .identificationNumber("72888777")
            .firstName("Alex")
            .lastName("Summers")
            .status(1)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
