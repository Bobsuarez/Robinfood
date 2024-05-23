package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.entities.OrderDailyEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class OrderDailyMappers {

    private OrderDailyMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static List<OrderDailyDTO> orderDailyEntityToOrderDailyDtoList(List<OrderDailyEntity> orderDailyEntityList) {
        return orderDailyEntityList.stream().map(
                OrderDailyMappers::orderDailyEntityToOrderDailyDto
        ).collect(Collectors.toList());
    }

    public static OrderDailyDTO orderDailyEntityToOrderDailyDto(
            OrderDailyEntity orderDailyEntity
    ) {
        return OrderDailyDTO.builder()
                .brandId(orderDailyEntity.getBrandId())
                .createdAt(orderDailyEntity.getCreatedAt())
                .deliveryTypeId(orderDailyEntity.getDeliveryTypeId())
                .id(orderDailyEntity.getId())
                .orderInvoiceNumber(orderDailyEntity.getOrderInvoiceNumber())
                .orderNumber(orderDailyEntity.getOrderNumber())
                .origin(OriginMappers.originEntityToOriginDto(orderDailyEntity.getOrigin()))
                .total(orderDailyEntity.getTotal())
                .user(OrderDetailUserMappers.toOrderDetailUserMappers(orderDailyEntity.getUser()))
                .build();
    }

}
