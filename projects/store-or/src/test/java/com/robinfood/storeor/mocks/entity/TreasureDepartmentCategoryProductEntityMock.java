package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasureDepartmentCategoryProductEntity;

import java.util.Arrays;
import java.util.List;

public class TreasureDepartmentCategoryProductEntityMock {

    public TreasureDepartmentCategoryProductEntity getDataDefault() {
        return TreasureDepartmentCategoryProductEntity.builder()
                .id(1L)
                .productId(1L)
                .properties(
                        TreasuryDepartmentCategoryProductPropertiesEntityMock
                                .aTreasuryDepartmentCategoryProductPropertiesResponseEntityId()
                )
                .treasuryDepartmentCategoryId(1L)
                .treasuryDepartmentId(1L)
                .build();
    }

    public List<TreasureDepartmentCategoryProductEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
