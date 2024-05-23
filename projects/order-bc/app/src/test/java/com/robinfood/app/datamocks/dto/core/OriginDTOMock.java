package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.OriginDTO;

public class OriginDTOMock {

    public static OriginDTO getDataDefault() {
        return OriginDTO.builder()
                .color("F0F0F0")
                .id(1L)
                .name("Rappi")
                .build();
    }

}
