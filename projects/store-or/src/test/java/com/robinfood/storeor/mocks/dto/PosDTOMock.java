package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.dtos.resolutions.ResolutionsIdsDTO;

public class PosDTOMock {

    public static PosDTO getDataDefault() {

        return PosDTO.builder()
                .code("AWS123")
                .name("Pos")
                .posTypeId(1L)
                .resolutionsIds(ResolutionsIdsDTO.builder().id(1L).resolutionId(1L).build())
                .status(true)
                .build();
    }

    public static PosDTO getPosDTOWithResolution() {

        return PosDTO.builder()
                .code("AWS123")
                .name("Pos")
                .posTypeId(1L)
                .resolutionsIds(ResolutionsIdsDTO.builder().id(1L).resolutionId(1L).build())
                .status(true)
                .build();
    }

    public static PosDTO getPosDTOWithOutResolution() {

        return PosDTO.builder()
                .code("AWS123")
                .name("Pos")
                .posTypeId(1L)
                .status(true)
                .build();
    }
}
