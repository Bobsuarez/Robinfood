package com.robinfood.changestatusor.mappers;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;

public final class ChangeOrderStatusDTOMapper {

    private ChangeOrderStatusDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ChangeOrderStatusDTO fromChangeStateOrderRespondEntityToChangeOrderStatusDTO(
            ChangeStateOrderRespondEntity changeStateOrderRespondEntity
    ){

        return ChangeOrderStatusDTO.builder()
                .brandId(changeStateOrderRespondEntity.getBrandId())
                .channelId(changeStateOrderRespondEntity.getChannelId())
                .deliveryIntegrationId(changeStateOrderRespondEntity.getDeliveryIntegrationId())
                .notes(changeStateOrderRespondEntity.getNotes())
                .orderId(changeStateOrderRespondEntity.getOrderId())
                .orderUuid(changeStateOrderRespondEntity.getOrderUuid())
                .origin(changeStateOrderRespondEntity.getOrigin())
                .statusCode(changeStateOrderRespondEntity.getStatusCode())
                .statusId(changeStateOrderRespondEntity.getStatusId())
                .transactionId(changeStateOrderRespondEntity.getTransactionId())
                .transactionUuid(changeStateOrderRespondEntity.getTransactionUuid())
                .userId(changeStateOrderRespondEntity.getUserId())
                .build();
    }
}
