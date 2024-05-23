package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.PortionDTO;
import com.robinfood.core.dtos.transactionrequestdto.ReplacementPortionDTO;
import com.robinfood.core.entities.PortionEntity;
import com.robinfood.core.entities.ReplacementPortionEntity;

public final class PortionMappers {

    private PortionMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static PortionEntity toPortionEntity(PortionDTO portionDTO) {
        final ReplacementPortionDTO replacementPortionDTO = portionDTO.getReplacementPortion();
        ReplacementPortionEntity replacementPortionEntity = null;
        if (replacementPortionDTO != null) {
            replacementPortionEntity = ReplacementPortionMappers.toReplacementPortionDTO(replacementPortionDTO);
        }

        return new PortionEntity(
                replacementPortionEntity,
                portionDTO.getFree(),
                portionDTO.getId(),
                portionDTO.getIsIncluded(),
                portionDTO.getName(),
                portionDTO.getPrice(),
                portionDTO.getUnitNumber(),
                portionDTO.getQuantity(),
                portionDTO.getSku()
        );
    }
}
