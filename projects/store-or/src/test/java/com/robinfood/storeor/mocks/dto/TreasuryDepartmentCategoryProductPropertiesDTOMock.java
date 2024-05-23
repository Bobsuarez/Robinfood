package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasuryDepartmentCategoryProductPropertiesDTO;

public class TreasuryDepartmentCategoryProductPropertiesDTOMock {

    public static TreasuryDepartmentCategoryProductPropertiesDTO
    aTreasuryDepartmentCategoryProductPropertiesResponseDTOId() {
        return TreasuryDepartmentCategoryProductPropertiesDTO.builder()
                .code("NCM")
                .id(1L)
                .status(Boolean.TRUE)
                .value("value")
                .build();
    }
}
