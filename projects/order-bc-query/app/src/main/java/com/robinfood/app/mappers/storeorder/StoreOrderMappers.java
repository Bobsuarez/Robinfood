package com.robinfood.app.mappers.storeorder;

import com.robinfood.core.dtos.storeorder.StoreOrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.StatusEntity;

import java.util.List;

public class StoreOrderMappers {

    public static StoreOrderDTO orderEntityToStoreOrderDTO(
            OrderEntity orderEntity,
            List<StatusEntity> statusEntities
    ) {

        return StoreOrderDTO.builder()
                .compensation(orderEntity.getCo2Total())
                .discounts(orderEntity.getDiscounts())
                .grossValue(orderEntity.getSubtotal())
                .id(orderEntity.getId())
                .netValue(orderEntity.getTotal())
                .orderInvoiceNumber(orderEntity.getOrderInvoiceNumber())
                .posId(orderEntity.getPosId())
                .statusCode(statusEntities.stream().filter(
                        status -> status.getId().equals(orderEntity.getStatusId())
                ).map(StatusEntity::getCode).findFirst().orElseThrow())
                .statusId(orderEntity.getStatusId())
                .taxes(orderEntity.getTaxes())
                .uuid(orderEntity.getUuid())
                .build();
    }
}
