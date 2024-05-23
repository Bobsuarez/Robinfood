package com.robinfood.app.mappers;

import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.dtos.OriginDTO;
import com.robinfood.core.dtos.UserDataDTO;

import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

public final class OrderDailyMapper {

    private OrderDailyMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderDailyDTO orderEntityToOrderDailyDto(
            OrderDTO orderDTO,
            List<OriginDTO> originEntityList,
            List<UserDataDTO> userDataDTOList
    ) {

        return OrderDailyDTO.builder()
                .brandId(orderDTO.getBrandId())
                .createdAt(orderDTO.getCreatedAt())
                .deliveryTypeId(orderDTO.getDeliveryTypeId())
                .id(orderDTO.getId())
                .orderInvoiceNumber(orderDTO.getOrderInvoiceNumber())
                .orderNumber(orderDTO.getOrderNumber())
                .origin(
                        originEntityList.stream().filter(
                                originEntity -> originEntity.getId().equals(orderDTO.getOriginId())
                        ).collect(Collectors.toList()).get(DEFAULT_INTEGER_VALUE)
                )
                .total(orderDTO.getTotal())
                .user(
                        userDataDTOList.stream().filter(
                                userDataDTO -> userDataDTO.getOrderId().equals(orderDTO.getId())
                        ).collect(Collectors.toList()).get(DEFAULT_INTEGER_VALUE)
                )
                .build();
    }

}
