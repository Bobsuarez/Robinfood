package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.entities.OrderDetailProductGroupEntity;
import com.robinfood.core.entities.OrderDetailProductGroupPortionEntity;

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
