package com.robinfood.repository.mocks;

import com.robinfood.core.entities.services.OriginEntity;

public final class OriginEntityMock {

    public static OriginEntity getDataDefault() {
        return OriginEntity.builder()
                .id(1L)
                .color("color")
                .name("origin")
                .build();
    }
}
