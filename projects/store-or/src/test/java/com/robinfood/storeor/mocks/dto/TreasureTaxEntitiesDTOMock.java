package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureTaxEntitiesDTO;
import com.robinfood.storeor.dtos.response.TreasureTaxEntityParametersDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureTaxEntitiesDTOMock {

    private TreasureTaxEntityParametersDTOMock parametersTaxesMock = new TreasureTaxEntityParametersDTOMock();

    private List<TreasureTaxEntityParametersDTO> parametersTaxes = parametersTaxesMock.getDataDefaultList();

    public TreasureTaxEntitiesDTO getDataDefault() {
        return  TreasureTaxEntitiesDTO.builder()
            .name("COFINSSN")
            .parameters(parametersTaxes)
            .build();
    }

    public List<TreasureTaxEntitiesDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
