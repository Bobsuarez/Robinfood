package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OriginDTO;
import com.robinfood.core.entities.services.OriginEntity;

public final class OriginMappers {

    private OriginMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OriginDTO originEntityToOriginDto(OriginEntity originEntity) {

        return  OriginDTO.builder()
                .color(originEntity.getColor())
                .id(originEntity.getId())
                .name(originEntity.getName())
                .build();
    }
}
