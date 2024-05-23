package com.robinfood.datamock.entity;

import com.robinfood.entities.api.routingintegration.ResponseRoutingIntegrationEntity;

public class ResponseRoutingIntegrationEntityMock {

    public static ResponseRoutingIntegrationEntity getDefault() {

        return ResponseRoutingIntegrationEntity.builder()
                .id(1L)
                .url("https//google.com")
                .uuid("ecd3042e-2967-4a2b-b020-a334d758f54c")
                .name("Change Status")
                .flow(FlowEntityMock.getDefault())
                .channelId(1L)
                .build();
    }
}
