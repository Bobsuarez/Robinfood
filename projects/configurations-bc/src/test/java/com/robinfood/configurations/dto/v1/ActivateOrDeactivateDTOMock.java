package com.robinfood.configurations.dto.v1;

public class ActivateOrDeactivateDTOMock {

    public static ActivateOrDeactivateDTO build() {
        return ActivateOrDeactivateDTO.builder()
                .status(Boolean.TRUE)
                .build();
    }
}
