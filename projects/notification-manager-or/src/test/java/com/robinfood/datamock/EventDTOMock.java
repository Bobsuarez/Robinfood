package com.robinfood.datamock;

import com.robinfood.dtos.EventDTO;

public class EventDTOMock {

    public static EventDTO getDefault() {

        return EventDTO.builder()
                .eventId("ID:bobsuarez-54998-1698780179214-3:1:1:1:1")
                .flowId(1L)
                .id(2L)
                .payload("{\"notes\": \"execute change status from localserver\", \"origin\": \"localserver\", \"userId\": 1, \"orderId\": 5493763, \"dateTime\": null, \"orderUid\": null, \"statusId\": null, \"channelId\": 10, \"orderUuid\": \"a4271e7d-416b-454c-98fa-604fdbb447ca\", \"statusCode\": \"ORDER_READY_TO_DELIVERED\", \"integrationId\": null, \"transactionId\": \"1234567\", \"couponReference\": null, \"transactionUuid\": \"1234567\"}")
                .build();
    }
}
