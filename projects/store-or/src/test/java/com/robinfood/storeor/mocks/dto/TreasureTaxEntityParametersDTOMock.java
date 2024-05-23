package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureTaxEntityParametersDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureTaxEntityParametersDTOMock {

    public TreasureTaxEntityParametersDTO getDataDefault() {
        return TreasureTaxEntityParametersDTO.builder()
            .name("CST")
            .value("49")
            .build();
    }

    public List<TreasureTaxEntityParametersDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
