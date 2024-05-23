package com.robinfood.datamock;

import com.robinfood.dtos.request.RequestChangeStateDTO;

public class RequestChangeStateDTOMock {

    public static RequestChangeStateDTO getDefault() {

        return RequestChangeStateDTO.builder()
                .deliveryIntegrationId("1234")
                .notes("cambio de estado de la orden")
                .orderId(5373848L)
                .orderUuid("ef5da32d-0f6d-4fc0-b923-71bc49e512fa")
                .origin("Local Server")
                .statusCode("ORDER_IN_PROGRESS")
                .statusId(2L)
                .userId(1L)
                .build();
    }

    public static RequestChangeStateDTO getNotComplete() {

        return RequestChangeStateDTO.builder()
                .deliveryIntegrationId("1234")
                .notes("cambio de estado de la orden")
                .origin("Local Server")
                .statusCode("ORDER_IN_PROGRESS")
                .statusId(2L)
                .build();
    }
}
