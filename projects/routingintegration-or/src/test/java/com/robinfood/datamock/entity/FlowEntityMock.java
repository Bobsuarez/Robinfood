package com.robinfood.datamock.entity;

import com.robinfood.entities.api.routingintegration.FlowEntity;

public class FlowEntityMock {
    public static FlowEntity getDefault() {

        return FlowEntity.builder()
                .code("flowIdCode")
                .flowId(1L)
                .name("flow")
                .build();
    }
}
