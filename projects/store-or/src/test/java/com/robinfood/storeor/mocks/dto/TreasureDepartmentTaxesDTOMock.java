package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureDepartmentTaxesDTO;
import com.robinfood.storeor.dtos.response.TreasureTaxEntitiesDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureDepartmentTaxesDTOMock {

    private TreasureTaxEntitiesDTOMock entitiesTaxesMock = new TreasureTaxEntitiesDTOMock();

    private List<TreasureTaxEntitiesDTO> entitiesTaxes = entitiesTaxesMock.getDataDefaultList();

    public TreasureDepartmentTaxesDTO getDataDefault() {
        return TreasureDepartmentTaxesDTO.builder()
            .id(1L)
            .entities(entitiesTaxes)
            .taxTypeId(1L)
            .build();
    }

    public List<TreasureDepartmentTaxesDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
