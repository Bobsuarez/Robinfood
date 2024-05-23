package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailRemovedPortionDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailRemovedPortionEntity;

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
