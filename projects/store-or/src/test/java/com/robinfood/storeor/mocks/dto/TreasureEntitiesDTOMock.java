package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureEntitiesDTO;
import com.robinfood.storeor.dtos.response.TreasureEntityParameterStoresDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureEntitiesDTOMock {

    private TreasureEntityParameterStoresDTOMock parameterStoresEntityMock = new TreasureEntityParameterStoresDTOMock();
    private List<TreasureEntityParameterStoresDTO> parameterStoresEntities = parameterStoresEntityMock.getDataDefaultList();

    public TreasureEntitiesDTO getDataDefault() {
        return TreasureEntitiesDTO.builder()
            .name("connection")
            .parameters(parameterStoresEntities)
            .build();
    }

    public List<TreasureEntitiesDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
