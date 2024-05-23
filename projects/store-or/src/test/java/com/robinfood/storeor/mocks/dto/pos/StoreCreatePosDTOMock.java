package com.robinfood.storeor.mocks.dto.pos;

import com.robinfood.storeor.dtos.configurationpos.PosDTO;
import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import com.robinfood.storeor.dtos.resolutions.ResolutionsIdsDTO;

import java.util.List;

public class StoreCreatePosDTOMock {

    public static StoreCreatePosDTO getDataDefault(boolean status){

        return StoreCreatePosDTO.builder()
                .pos(List.of(PosDTO.builder()
                                .code("AWS12345")
                                .name("TEST")
                                .posTypeId(1L)
                                .resolutionsIds(ResolutionsIdsDTO.builder().id(1L).resolutionId(1L).build())
                                .status(status)
                        .build()))
                .storeId(1L)
                .build();
    }
}
