package com.robinfood.configurations.samples;

import com.robinfood.configurations.dto.v1.ResolutionsIdsDTO;
import com.robinfood.configurations.dto.v1.StorePosDTO;
import com.robinfood.configurations.dto.v1.models.CreatePosDTO;

import java.time.LocalDateTime;
import java.util.List;

public class StorePosDTOSample {

    private static final Long TEST_LONG = 1L;

    private static final String TEST = "TEST";

    public static StorePosDTO getStorePosDTO() {

        return StorePosDTO.builder()
                .storeId(TEST_LONG)
                .pos(List.of(CreatePosDTO.builder()
                        .code(TEST)
                        .name(TEST)
                        .posTypeId(TEST_LONG)
                        .status(true)
                        .resolutionsIds(ResolutionsIdsDTO.builder()
                                .id(TEST_LONG)
                                .resolutionId(TEST_LONG).build())
                        .build()))
                .build();
    }

    public static StorePosDTO getStorePosResolutionsIdsNUllDTO() {

        return StorePosDTO.builder()
                .storeId(TEST_LONG)
                .pos(List.of(CreatePosDTO.builder()
                        .code(TEST)
                        .name(TEST)
                        .posTypeId(TEST_LONG)
                        .status(true)
                        .resolutionsIds(ResolutionsIdsDTO.builder().resolutionId(null)
                                .id(null)
                                .build())
                        .build()))
                .build();
    }
}
