package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class OrderHistoryItemDTO {

    final OrderBrandDTO brand;

    final LocalDateTime createdAt;

    final Long deliveryTypeId;

    final Long id;

    final String orderInvoiceNumber;

    final String orderNumber;

    final OriginDTO origin;

    final Double total;

    final OrderStatusDTO status;

    final UserDataDTO user;

}
