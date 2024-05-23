package com.robinfood.storeor.mocks.entity.resolutions;

import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;

public class ActivateOrDeactivateEntityMock {

    public ActivateOrDeactivateEntity defaultData() {
        return ActivateOrDeactivateEntity
                .builder()
                .status(Boolean.FALSE)
                .build();
    }
}
