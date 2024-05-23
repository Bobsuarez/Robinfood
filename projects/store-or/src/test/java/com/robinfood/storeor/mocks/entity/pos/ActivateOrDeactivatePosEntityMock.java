package com.robinfood.storeor.mocks.entity.pos;

import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;

public class ActivateOrDeactivatePosEntityMock {

    public ActivateOrDeactivatePosEntity defaultData() {
        return ActivateOrDeactivatePosEntity
                .builder()
                .status(Boolean.FALSE)
                .build();
    }
}
