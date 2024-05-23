package com.robinfood.mocks.dtos.v1.request;

import com.robinfood.dtos.v1.request.EnabledDisabledResolutionDTO;

public class EnableDisableResolutionDTOMock {

    public static EnabledDisabledResolutionDTO build() {
        return EnabledDisabledResolutionDTO.builder()
                .status(Boolean.TRUE)
                .build();
    }
}
