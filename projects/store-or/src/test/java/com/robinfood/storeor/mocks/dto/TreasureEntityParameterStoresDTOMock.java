package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.response.TreasureEntityParameterStoresDTO;

import java.util.Arrays;
import java.util.List;

public class TreasureEntityParameterStoresDTOMock {

    public TreasureEntityParameterStoresDTO getDataDefault() {
        return TreasureEntityParameterStoresDTO.builder()
            .name("url")
            .value("http://localhost")
            .build();
    }

    public List<TreasureEntityParameterStoresDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
