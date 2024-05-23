package com.robinfood.datamock;

import com.robinfood.dtos.request.RequestPublishEventDTO;

public class RequestPublishEventDTOMock {

    public static RequestPublishEventDTO getDefault() {

        return RequestPublishEventDTO.builder()
                .couponReference("1234")
                .dateTime("0-06-2023")
                .eventId("asf245-hfdjd374-008474")
                .integrationId(1L)
                .orderId(5373848L)
                .orderUid("ef5da32d-0f6d-4fc0-b923-71bc49e512fa")
                .orderUuid("ef5da32d-0f6d-4fc0-b923-71bc49e512fa")
                .statusCode("ORDER_IN_PROGRESS")
                .transactionId(1L)
                .transactionUuid("ef5da32d-0f6d-4fc0-b923-71bc49e512fa")
                .build();
    }

    public static RequestPublishEventDTO getNotComplete() {

        return RequestPublishEventDTO.builder()
                .couponReference("1234")
                .dateTime("0-06-2023")
                .eventId("asf245-hfdjd374-008474")
                .integrationId(1L)
                .orderUid("ef5da32d-0f6d-4fc0-b923-71bc49e512fa")
                .orderUuid("ef5da32d-0f6d-4fc0-b923-71bc49e512fa")
                .statusCode("ORDER_IN_PROGRESS")
                .transactionId(1L)
                .transactionUuid("ef5da32d-0f6d-4fc0-b923-71bc49e512fa")
                .build();
    }
}
