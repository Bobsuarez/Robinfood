package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailChangedPortionDTO;
import com.robinfood.core.entities.OrderDetailChangedPortionEntity;

import java.util.Objects;

public final class OrderDetailChangedPortionMappers {

    private OrderDetailChangedPortionMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderDetailChangedPortionDTO toOrderDetailChangedPortionDTO(
            OrderDetailChangedPortionEntity orderDetailChangedPortionEntity
    ) {
        if (Objects.isNull(orderDetailChangedPortionEntity)) {
            return null;
        }

        return new OrderDetailChangedPortionDTO(
                orderDetailChangedPortionEntity.getId(),
                orderDetailChangedPortionEntity.getName(),
                orderDetailChangedPortionEntity.getProductId(),
                orderDetailChangedPortionEntity.getSku(),
                orderDetailChangedPortionEntity.getUnitId(),
                orderDetailChangedPortionEntity.getUnitNumber()
        );
    }
}
