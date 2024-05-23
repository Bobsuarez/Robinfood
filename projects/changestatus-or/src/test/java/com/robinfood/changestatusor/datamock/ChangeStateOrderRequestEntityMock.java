package com.robinfood.changestatusor.datamock;

import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;

public class ChangeStateOrderRequestEntityMock {

    public static ChangeStateOrderRequestEntity getDefault(){

        return ChangeStateOrderRequestEntity.builder()
                .brandId("27")
                .channelId(10L)
                .deliveryIntegrationId("1")
                .notes("Test")
                .orderId(123456L)
                .orderUid("dlknzvlasknir4bnscbs")
                .orderUuid("dlknzvlasknir4bnscbs")
                .origin("local server")
                .statusCode("ORDER_IN_PROGESS")
                .statusId(2L)
                .transactionId(3L)
                .transactionUuid("dlknzvlasknir4bnscbs")
                .userId(1L)
                .build();
    }
}
