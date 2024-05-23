package com.robinfood.datamock;

import com.robinfood.dtos.FlowDTO;

public class FlowDTOMock {
    public static FlowDTO getDefault() {

        return FlowDTO.builder()
                .id(2L)
                .code("CHANGE_STATUS")
                .name("name")
                .build();
    }

}
