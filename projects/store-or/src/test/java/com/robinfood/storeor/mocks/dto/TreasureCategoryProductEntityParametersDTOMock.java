package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureCategoryProductEntityParametersDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureCategoryProductEntityParametersDTOMock {

    public TreasureCategoryProductEntityParametersDTO getDataDefault() {
        return TreasureCategoryProductEntityParametersDTO.builder()
            .name("code")
            .value("5101")
            .build();
    }

    public List<TreasureCategoryProductEntityParametersDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
