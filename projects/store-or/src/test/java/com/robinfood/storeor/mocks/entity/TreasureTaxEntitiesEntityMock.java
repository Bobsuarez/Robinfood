package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureTaxEntitiesEntity;
import com.robinfood.storeor.entities.TreasureTaxEntityParametersEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureTaxEntitiesEntityMock {

    private TreasureTaxEntityParametersEntityMock parametersTaxesMock = new TreasureTaxEntityParametersEntityMock();

    private List<TreasureTaxEntityParametersEntity> parametersTaxes = parametersTaxesMock.getDataDefaultList();

    public TreasureTaxEntitiesEntity getDataDefault() {
        return  TreasureTaxEntitiesEntity.builder()
            .name("COFINSSN")
            .parameters(parametersTaxes)
            .build();
    }

    public List<TreasureTaxEntitiesEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
