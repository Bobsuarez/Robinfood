package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.configuration.StoresDTO;

import java.util.List;

public class StoresDTOMock {

    public static StoresDTO getDataDefault() {

        return StoresDTO.builder()
                .content(List.of(StoreDTO.builder().build()))
                .build();
    }
}
