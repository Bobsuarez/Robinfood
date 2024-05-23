package com.robinfood.configurations.dto.v1;

import com.robinfood.configurations.dto.v1.models.UpdatePosDTO;

public class UpdatePosDTOMock {

    public static UpdatePosDTO getUpdatePosDTOStatusTrue() {

        return UpdatePosDTO.builder()
                .code("QWERTY")
                .name("POS Test")
                .posTypeId(1L)
                .status(true)
                .resolutionsIds(ResolutionsIdsDTO.builder().resolutionId(null)
                        .id(null)
                        .build())
                .build();
    }

    public static UpdatePosDTO getUpdatePosDTOStatusFalse() {

        return UpdatePosDTO.builder()
                .code("QWERTY")
                .name("POS Test")
                .posTypeId(1L)
                .status(false)
                .resolutionsIds(ResolutionsIdsDTO.builder().resolutionId(null)
                        .id(null)
                        .build())
                .build();
    }
}
