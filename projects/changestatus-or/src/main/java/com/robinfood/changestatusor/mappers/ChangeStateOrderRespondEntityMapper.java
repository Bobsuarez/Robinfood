package com.robinfood.changestatusor.mappers;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;

public final class ChangeStateOrderRespondEntityMapper {

    private ChangeStateOrderRespondEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ChangeStateOrderRequestEntity fromChangeOrderStatusDTOToChangeStateOrderRespondEntity(
            ChangeOrderStatusDTO changeOrderStatusDTO
    ){

        return ChangeStateOrderRequestEntity.builder()
                .brandId(changeOrderStatusDTO.getBrandId())
                .channelId(changeOrderStatusDTO.getChannelId())
                .deliveryIntegrationId(changeOrderStatusDTO.getDeliveryIntegrationId())
                .notes(changeOrderStatusDTO.getNotes())
                .orderId(changeOrderStatusDTO.getOrderId())
                .orderUuid(changeOrderStatusDTO.getOrderUuid())
                .origin(changeOrderStatusDTO.getOrigin())
                .statusCode(changeOrderStatusDTO.getStatusCode())
                .statusId(changeOrderStatusDTO.getStatusId())
                .transactionId(changeOrderStatusDTO.getTransactionId())
                .transactionUuid(changeOrderStatusDTO.getTransactionUuid())
                .userId(changeOrderStatusDTO.getUserId())
                .build();
    }
}
