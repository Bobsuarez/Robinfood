package com.robinfood.datamock.entity;

import com.robinfood.entities.api.changeorderstatus.ResponseChangeOrderStatusEntity;

public class ResponseChangeOrderStatusEntityMock {

    public static ResponseChangeOrderStatusEntity getDefault() {

        return ResponseChangeOrderStatusEntity.builder()
                .brandId("flowIdCode")
                .notes("notes")
                .orderId("flow")
                .origin("flow")
                .statusCode("flow")
                .userId(1L)
                .build();
    }
}
