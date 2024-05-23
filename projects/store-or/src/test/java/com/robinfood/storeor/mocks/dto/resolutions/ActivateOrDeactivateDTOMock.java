package com.robinfood.storeor.mocks.dto.resolutions;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;

public class ActivateOrDeactivateDTOMock {

    public ActivateOrDeactivateDTO defaultData() {
        return ActivateOrDeactivateDTO
                .builder()
                .status(Boolean.FALSE)
                .build();
    }
}
