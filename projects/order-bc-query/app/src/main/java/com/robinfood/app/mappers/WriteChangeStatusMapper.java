package com.robinfood.app.mappers;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WriteChangeStatusMapper {

    public WriteChangeStatusDTO changeOrderStatusDTOToWriteChangeStatusDTO(ChangeOrderStatusDTO changeOrderStatusDTO) {

        return WriteChangeStatusDTO
                .builder()
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
