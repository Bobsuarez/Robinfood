package com.robinfood.core.mappers;

import com.robinfood.core.dtos.StatusCustomFilterDTO;
import com.robinfood.core.enums.StatusCustomFilterEnum;

public final class StatusCustomFilterMappers {

    private StatusCustomFilterMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static StatusCustomFilterDTO statusFilterEnumToStatusFilterDTO (
            StatusCustomFilterEnum statusCustomFilterEnum
    ) {

        return StatusCustomFilterDTO.builder()
                .code(statusCustomFilterEnum.getCode())
                .id(statusCustomFilterEnum.getId())
                .build();
    }
}
