package com.robinfood.storeor.mocks.entity;

import com.robinfood.storeor.entities.TreasuryDepartmentCategoryProductPropertiesEntity;

public class TreasuryDepartmentCategoryProductPropertiesEntityMock {

    public static TreasuryDepartmentCategoryProductPropertiesEntity
    aTreasuryDepartmentCategoryProductPropertiesResponseEntityId() {
        return TreasuryDepartmentCategoryProductPropertiesEntity.builder()
                .code("NCM")
                .id(1L)
                .status(Boolean.TRUE)
                .value("value")
                .build();
    }
}
