package com.robinfood.changestatusbc.mappers;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WriteChangeStatusMapper {

    public WriteChangeStatusDTO changeOrderStatusDTOToWriteChangeStatusDTO(ChangeOrderStatusDTO changeOrderStatusDTO) {

        return WriteChangeStatusDTO
                .builder()
                .channelId(changeOrderStatusDTO.getChannelId())
                .couponReference(null)
                .dateTime(LocalDateTime.now().toString())
                .orderId(changeOrderStatusDTO.getOrderId())
                .orderUid(changeOrderStatusDTO.getOrderUid())
                .orderUuid(changeOrderStatusDTO.getOrderUuid())
                .integrationId(changeOrderStatusDTO.getDeliveryIntegrationId())
                .statusCode(changeOrderStatusDTO.getStatusCode())
                .transactionUuid(changeOrderStatusDTO.getTransactionUuid())
                .transactionId(changeOrderStatusDTO.getTransactionId())
                .build();
    }
}
