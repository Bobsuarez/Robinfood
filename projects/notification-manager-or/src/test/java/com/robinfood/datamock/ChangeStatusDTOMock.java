package com.robinfood.datamock;

import com.robinfood.dtos.ChangeStatusDTO;

public class ChangeStatusDTOMock {

    public static ChangeStatusDTO getDefault() {

        return ChangeStatusDTO.builder()
                .channelId(10L)
                .couponReference(null)
                .dateTime(null)
                .integrationId(null)
                .notes("execute change status from localserver")
                .orderId(5493763L)
                .orderUid(null)
                .orderUuid("a4271e7d-416b-454c-98fa-604fdbb447ca")
                .origin("localserver")
                .statusCode("ORDER_READY_TO_DELIVERED")
                .statusId(null)
                .transactionId("1234567")
                .transactionUuid("123456789")
                .build();
    }
}
