package com.robinfood.datamock;

import com.robinfood.dtos.request.RequestRoutingIntegrationDTO;

public class RequestRoutingIntegrationDTOMock {

    public static RequestRoutingIntegrationDTO getDefault() {

        return RequestRoutingIntegrationDTO.builder()
                .notes("Se entrega la orden al domicilario")
                .channelId(10L)
                .orderId(9087824L)
                .orderUuid("b3ba6d34-f9ba-4483-b648-15f3990d7b4a")
                .origin("Local Server")
                .statusCode("ORDER_PROCESS")
                .statusId(2L)
                .storeId(27L)
                .userId(1L)
                .build();
    }

    public static RequestRoutingIntegrationDTO getDefaultNotComplete() {

        return RequestRoutingIntegrationDTO.builder()
                .notes("Se entrega la orden al domicilario")
                .orderUuid("b3ba6d34-f9ba-4483-b648-15f3990d7b4a")
                .origin("Local Server")
                .statusCode("ORDER_PROCESS")
                .statusId(2L)
                .userId(1L)
                .build();
    }
}
