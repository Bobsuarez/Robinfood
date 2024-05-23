package com.robinfood.app.mappers;

import com.robinfood.core.dtos.OriginDTO;
import com.robinfood.core.entities.OriginEntity;

public final class OriginMapper {

    private OriginMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static OriginDTO originEntityToOriginDto(OriginEntity originEntity) {

        return OriginDTO.builder()
                .color(originEntity.getColor())
                .id(originEntity.getId())
                .name(originEntity.getName())
                .build();
    }

}
