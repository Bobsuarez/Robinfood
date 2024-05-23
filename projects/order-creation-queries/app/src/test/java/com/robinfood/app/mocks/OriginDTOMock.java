package com.robinfood.app.mocks;

import com.robinfood.core.dtos.OriginDTO;

public final class OriginDTOMock {

    public static OriginDTO getDataDefault() {

        return OriginDTO.builder()
                .id(1L)
                .color("color")
                .name("origin")
                .build();
    }
}
