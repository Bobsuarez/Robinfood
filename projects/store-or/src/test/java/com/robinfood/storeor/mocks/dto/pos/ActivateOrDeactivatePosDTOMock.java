package com.robinfood.storeor.mocks.dto.pos;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;

public class ActivateOrDeactivatePosDTOMock {

    public ActivateOrDeactivatePosDTO defaultData() {
        return ActivateOrDeactivatePosDTO
                .builder()
                .status(Boolean.FALSE)
                .build();
    }
}
