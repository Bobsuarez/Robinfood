package com.robinfood.mocks.dtos.v1.request;

import com.robinfood.dtos.v1.request.StoreResolutionDTO;

import java.util.List;

public class StoreResolutionDTOMock {

    public static StoreResolutionDTO build() {
        return StoreResolutionDTO.builder()
                .resolutions(List.of(ResolutionDTOMock.build()))
                .storeId(1L)
                .build();
    }

    public static StoreResolutionDTO buildWithStoreIdIsNull() {
        return StoreResolutionDTO.builder()
                .resolutions(List.of(ResolutionDTOMock.build()))
                .storeId(null)
                .build();
    }

    public static StoreResolutionDTO buildWithPosIdIsNull() {
        return StoreResolutionDTO.builder()
                .resolutions(List.of(ResolutionDTOMock.buildWithPosId()))
                .storeId(1L)
                .build();
    }
}
