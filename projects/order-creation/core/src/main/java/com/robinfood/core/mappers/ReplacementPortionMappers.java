package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.ReplacementPortionDTO;
import com.robinfood.core.entities.ReplacementPortionEntity;

public final class ReplacementPortionMappers {

    private ReplacementPortionMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ReplacementPortionEntity toReplacementPortionDTO(
            ReplacementPortionDTO replacementPortionDTO
    ) {
        return new ReplacementPortionEntity(
                replacementPortionDTO.getId(),
                replacementPortionDTO.getName()
        );
    }

}
