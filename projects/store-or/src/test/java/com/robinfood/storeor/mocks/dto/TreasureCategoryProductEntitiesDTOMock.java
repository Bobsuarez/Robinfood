package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureCategoryProductEntitiesDTO;
import com.robinfood.storeor.dtos.response.TreasureCategoryProductEntityParametersDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureCategoryProductEntitiesDTOMock {

    private TreasureCategoryProductEntityParametersDTOMock parametersEntityMock= new TreasureCategoryProductEntityParametersDTOMock();
    private List<TreasureCategoryProductEntityParametersDTO> parametersProduct = parametersEntityMock.getDataDefaultList();

    public TreasureCategoryProductEntitiesDTO getDataDefault() {
        return TreasureCategoryProductEntitiesDTO.builder()
            .name("CFOP")
            .parameters(parametersProduct)
            .build();
    }

    public List<TreasureCategoryProductEntitiesDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
