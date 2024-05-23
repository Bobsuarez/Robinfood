package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureEntityParameterStoresEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureEntityParameterStoresEntityMock {

    public TreasureEntityParameterStoresEntity getDataDefault() {
        return TreasureEntityParameterStoresEntity.builder()
            .name("url")
            .value("http://localhost")
            .build();
    }

    public List<TreasureEntityParameterStoresEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }

}
