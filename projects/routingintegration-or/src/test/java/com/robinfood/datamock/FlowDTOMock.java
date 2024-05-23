package com.robinfood.datamock;

import com.robinfood.dtos.routinintegration.FlowDTO;

public class FlowDTOMock {

    public static FlowDTO getDefault() {

        return FlowDTO.builder()
                .code("CHANGE_STATUS")
                .flowId(1L)
                .name("Change Status")
                .build();
    }
}
