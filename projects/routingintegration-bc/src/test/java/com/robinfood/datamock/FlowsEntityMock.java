package com.robinfood.datamock;

import com.robinfood.entities.FlowsEntity;

public class FlowsEntityMock {

    public static FlowsEntity build() {
        return FlowsEntity.builder()
                .code("code")
                .name("name")
                .id(1L)
                .description("description")
                .build();
    }

}
