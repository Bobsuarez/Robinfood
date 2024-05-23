package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureTaxEntityParametersEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureTaxEntityParametersEntityMock {

    public TreasureTaxEntityParametersEntity getDataDefault() {
        return TreasureTaxEntityParametersEntity.builder()
            .name("CST")
            .value("49")
            .build();
    }

    public List<TreasureTaxEntityParametersEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
