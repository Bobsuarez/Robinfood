package com.robinfood.datamock;

import com.robinfood.dtos.PropertyDTO;

public class PropertyDTOMock {

    public static PropertyDTO getDefault() {

        return PropertyDTO.builder()
                .description("URL de cambio de estado")
                .id(1L)
                .name("url/api/v1/message/channel/pos/change-status")
                .key("URL")
                .build();
    }
}
