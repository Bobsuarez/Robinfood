package com.robinfood.app.mappers.ordercancellation;

import com.robinfood.app.enums.StatusOrderCancellationEnum;
import com.robinfood.core.dtos.report.ordercancellation.GetOrderCancellationResponseDTO;
import com.robinfood.core.dtos.report.ordercancellation.OrderUserDataCancellationDTO;
import com.robinfood.core.dtos.report.ordercancellation.statusOrderCancellationDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderUserDataEntity;

public class GetOrderCancellationMapper {

    public static GetOrderCancellationResponseDTO toGetOrderCancellationResponseDTO(
            String integrationId,
            OrderEntity orderEntity,
            OrderUserDataEntity orderUserData,
            String prefix
    ) {

        return GetOrderCancellationResponseDTO.builder()
                .brandId(orderEntity.getBrandId())
                .discounts(orderEntity.getDiscounts())
                .localDate(orderEntity.getLocalDate())
                .localTime(orderEntity.getLocalTime())
                .orderId(orderEntity.getId())
                .orderIdIntegration(integrationId)
                .orderInvoiceNumber(orderEntity.getOrderInvoiceNumber())
                .orderUID(orderEntity.getUid())
                .orderUUID(orderEntity.getUuid())
                .originId(orderEntity.getOriginId())
                .paid(orderEntity.getPaid())
                .posResolutionsPrefix(prefix)
                .statusOrderCancellation(statusOrderCancellationDTO.builder()
                                .codeStatus(StatusOrderCancellationEnum
                                        .parseToStatus(orderEntity.getStatusId()))
                                .statusId(orderEntity.getStatusId())
                                .build())
                .subtotal(orderEntity.getSubtotal())
                .storeId(orderEntity.getStoreId())
                .taxes(orderEntity.getTaxes())
                .total(orderEntity.getTotal())
                .totalCo2(orderEntity.getCo2Total())
                .userData(OrderUserDataCancellationDTO.builder()
                        .firstName(orderUserData.getFirstName())
                        .lastName(orderUserData.getLastName())
                        .mobile(orderUserData.getMobile())
                        .build())
                .build();
    }
}
