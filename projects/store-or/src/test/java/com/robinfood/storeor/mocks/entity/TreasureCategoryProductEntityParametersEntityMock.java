package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureCategoryProductEntityParametersEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureCategoryProductEntityParametersEntityMock {

    public TreasureCategoryProductEntityParametersEntity getDataDefault() {
       return TreasureCategoryProductEntityParametersEntity.builder()
            .name("code")
            .value("5101")
            .build();
    }

    public List<TreasureCategoryProductEntityParametersEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
