package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;
import com.robinfood.core.entities.OrderDetailRemovedPortionEntity;

public final class OrderDetailRemovedPortionMappers {

    private OrderDetailRemovedPortionMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderDetailRemovedPortionDTO toOrderDetailRemovedPortionDTO(
            OrderDetailRemovedPortionEntity orderDetailRemovedPortionEntity
    ) {
        return new OrderDetailRemovedPortionDTO(
                orderDetailRemovedPortionEntity.getId(),
                orderDetailRemovedPortionEntity.getName()
        );
    }
}
