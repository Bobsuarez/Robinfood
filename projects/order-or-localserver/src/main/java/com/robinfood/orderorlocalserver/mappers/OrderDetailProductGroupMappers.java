package com.robinfood.orderorlocalserver.mappers;


import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupPortionDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductGroupEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductGroupPortionEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class OrderDetailProductGroupMappers {

    private OrderDetailProductGroupMappers() {
        throw new IllegalStateException("Utility class");
    }

    private static List<OrderDetailProductGroupPortionDTO> convertFromEntityToDtoByOrderDetailProductGroupPortionDTOS(
            List<OrderDetailProductGroupPortionEntity> orderDetailProductGroupPortionEntities
    ) {
        return Optional.ofNullable(orderDetailProductGroupPortionEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailProductGroupPortionMappers::toOrderDetailProductGroupPortionDTO)
                .collect(Collectors.toList());
    }

    public static OrderDetailProductGroupDTO toOrderDetailProductGroupDTO(
            OrderDetailProductGroupEntity orderDetailProductGroupEntity
    ) {
        return new OrderDetailProductGroupDTO(
                orderDetailProductGroupEntity.getId(),
                orderDetailProductGroupEntity.getName(),
                convertFromEntityToDtoByOrderDetailProductGroupPortionDTOS(
                        orderDetailProductGroupEntity.getPortions()
                ),
                orderDetailProductGroupEntity.getRemovedPortions()
                        .stream()
                        .map(OrderDetailRemovedPortionMappers::toOrderDetailRemovedPortionDTO)
                        .collect(Collectors.toList()),
                orderDetailProductGroupEntity.getSku()
        );
    }
}
