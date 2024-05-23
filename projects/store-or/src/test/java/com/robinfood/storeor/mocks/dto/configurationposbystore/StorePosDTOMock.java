package com.robinfood.storeor.mocks.dto.configurationposbystore;

import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;

import java.util.Collections;
import java.util.List;

public class StorePosDTOMock {

    public List<StorePosDTO> storePosDTOs = List.of(
            StorePosDTO.builder()
                    .id(1L)
                    .name("caja1")
                    .resolutions(Collections.emptyList())
                    .build(),
            StorePosDTO.builder()
                    .id(2L)
                    .name("caja2")
                    .resolutions(Collections.emptyList())
                    .build()
    );
}
