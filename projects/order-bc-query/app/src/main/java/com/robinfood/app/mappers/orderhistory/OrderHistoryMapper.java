package com.robinfood.app.mappers.orderhistory;

import com.robinfood.core.dtos.OrderBrandDTO;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.OriginDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryMapper {

    public static List<OrderHistoryItemDTO> orderEntityToOrderHistoryItemDTO(
            List<OrderEntity> orderEntities,
            List<OrderStatusDTO> statusDTOS,
            List<UserDataDTO> userDataDTOS
    ) {

        return orderEntities.stream().map(
                orderEntity -> OrderHistoryItemDTO.builder()
                        .brand(orderBrandEntityToOrderBrandDTO(orderEntity))
                        .createdAt(orderEntity.getCreatedAt())
                        .deliveryTypeId(orderEntity.getDeliveryTypeId())
                        .id(orderEntity.getId())
                        .orderInvoiceNumber(orderEntity.getOrderInvoiceNumber())
                        .orderNumber(orderEntity.getOrderNumber())
                        .origin(orderOriginEntityToOriginDTO(orderEntity))
                        .total(orderEntity.getTotal())
                        .status(orderStatusDTOToOrderHistoryStatusDTO(statusDTOS, orderEntity.getStatusId()))
                        .user(userDataDTOToOrderHistoryUserDTO(userDataDTOS, orderEntity.getUserId()))
                        .build()
        ).collect(Collectors.toList());

    }

    private static OrderBrandDTO orderBrandEntityToOrderBrandDTO(OrderEntity orderEntity) {

        return OrderBrandDTO.builder()
                .id(orderEntity.getBrandId())
                .name(orderEntity.getBrandName())
                .build();
    }

    private static OriginDTO orderOriginEntityToOriginDTO(OrderEntity orderEntity) {

        return OriginDTO.builder()
                .id(orderEntity.getOriginId())
                .name(orderEntity.getOriginName())
                .build();
    }

    private static OrderStatusDTO orderStatusDTOToOrderHistoryStatusDTO(
            List<OrderStatusDTO> statusDTOS,
            Long orderStatus
    ) {

        final OrderStatusDTO orderStatusDTO = statusDTOS.stream().filter(
                        status -> status.getId().equals(orderStatus)
                ).findFirst().get();

        return OrderStatusDTO.builder()
                .id(orderStatusDTO.getId())
                .name(orderStatusDTO.getName())
                .build();
    }

    private static UserDataDTO userDataDTOToOrderHistoryUserDTO(
            List<UserDataDTO> userDataDTOS,
            Long userId
    ) {

        final UserDataDTO userDataDTO = userDataDTOS.stream().filter(
                user -> user.getId().equals(userId)
        ).findFirst().get();

        return UserDataDTO.builder()
                .id(userDataDTO.getId())
                .email(userDataDTO.getEmail())
                .mobile(userDataDTO.getMobile())
                .firstName(userDataDTO.getFirstName())
                .lastName(userDataDTO.getLastName())
                .build();
    }

}
