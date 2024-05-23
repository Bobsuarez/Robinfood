package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureCategoryProductEntitiesEntity;
import com.robinfood.storeor.entities.TreasureCategoryProductEntityParametersEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureCategoryProductEntitiesEntityMock {

    private TreasureCategoryProductEntityParametersEntityMock parametersEntityMock= new TreasureCategoryProductEntityParametersEntityMock();
    private List<TreasureCategoryProductEntityParametersEntity> parametersProduct = parametersEntityMock.getDataDefaultList();

    public TreasureCategoryProductEntitiesEntity getDataDefault() {
        return TreasureCategoryProductEntitiesEntity.builder()
            .name("CFOP")
            .parameters(parametersProduct)
            .build();
    }

    public List<TreasureCategoryProductEntitiesEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }

}
