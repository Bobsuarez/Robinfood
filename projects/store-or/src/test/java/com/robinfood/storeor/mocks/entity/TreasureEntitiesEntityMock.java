package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureEntitiesEntity;
import com.robinfood.storeor.entities.TreasureEntityParameterStoresEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureEntitiesEntityMock {

    private TreasureEntityParameterStoresEntityMock parameterStoresEntityMock = new TreasureEntityParameterStoresEntityMock();
    private List<TreasureEntityParameterStoresEntity> parameterStoresEntities = parameterStoresEntityMock.getDataDefaultList();

    public TreasureEntitiesEntity getDataDefault() {
       return TreasureEntitiesEntity.builder()
            .name("connection")
            .parameters(parameterStoresEntities)
            .build();
    }

    public List<TreasureEntitiesEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
