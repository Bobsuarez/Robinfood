package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureDepartmentTaxesEntity;
import com.robinfood.storeor.entities.TreasureTaxEntitiesEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureDepartmentTaxesEntityMock {

    private TreasureTaxEntitiesEntityMock entitiesTaxesMock = new TreasureTaxEntitiesEntityMock();

    private List<TreasureTaxEntitiesEntity> entitiesTaxes = entitiesTaxesMock.getDataDefaultList();

    public TreasureDepartmentTaxesEntity getDataDefault() {
        return TreasureDepartmentTaxesEntity.builder()
            .id(1L)
            .entities(entitiesTaxes)
            .taxTypeId(1L)
            .build();
    }

    public List<TreasureDepartmentTaxesEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
