package com.robinfood.mappers;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.dtos.request.RequestRoutingIntegrationDTO;

public final class RequestChangeOrderStatusDTOMapper {

    private RequestChangeOrderStatusDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static RequestChangeOrderStatusDTO buildToRequestChangeOrderStatusDTO(
            RequestRoutingIntegrationDTO requestRoutingIntegrationDTO
    ) {

        return RequestChangeOrderStatusDTO.builder()
                .channelId(requestRoutingIntegrationDTO.getChannelId())
                .notes(requestRoutingIntegrationDTO.getNotes())
                .orderId(requestRoutingIntegrationDTO.getOrderId())
                .orderUuid(requestRoutingIntegrationDTO.getOrderUuid())
                .origin(requestRoutingIntegrationDTO.getOrigin())
                .statusCode(requestRoutingIntegrationDTO.getStatusCode())
                .statusId(requestRoutingIntegrationDTO.getStatusId())
                .storeId(requestRoutingIntegrationDTO.getStoreId())
                .userId(requestRoutingIntegrationDTO.getUserId())
                .build();
    }
}
