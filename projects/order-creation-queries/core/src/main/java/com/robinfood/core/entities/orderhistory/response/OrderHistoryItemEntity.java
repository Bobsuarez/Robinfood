package com.robinfood.core.entities.orderhistory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderHistoryItemEntity {

    final OrderHistoryBrandEntity brand;

    final String createdAt;

    final Long deliveryTypeId;

    final Long id;

    final String orderInvoiceNumber;

    final String orderNumber;

    final OrderHistoryOriginEntity origin;

    final Double total;

    final OrderHistoryStatusEntity status;

    final UserHistoryEntity user;

}
