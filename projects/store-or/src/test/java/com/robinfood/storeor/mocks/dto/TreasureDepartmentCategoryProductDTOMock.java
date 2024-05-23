package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureCategoryProductEntitiesDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentCategoryProductDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureDepartmentCategoryProductDTOMock {

    private TreasureCategoryProductEntitiesDTOMock entitiesProductMock = new TreasureCategoryProductEntitiesDTOMock();
    private List<TreasureCategoryProductEntitiesDTO> entitiesProduct = entitiesProductMock.getDataDefaultList();

    public TreasureDepartmentCategoryProductDTO getDataDefault() {
        return TreasureDepartmentCategoryProductDTO.builder()
                .id(1L)
                .productId(1L)
                .properties(
                        TreasuryDepartmentCategoryProductPropertiesDTOMock
                                .aTreasuryDepartmentCategoryProductPropertiesResponseDTOId()
                )
                .treasuryDepartmentCategoryId(1L)
                .treasuryDepartmentId(1L)
                .build();
    }

    public List<TreasureDepartmentCategoryProductDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
