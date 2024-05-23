package com.robinfood.core.dtos.orderhistory.response;

import com.robinfood.core.dtos.OriginDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class OrderHistoryItemDTO {

    final OrderHistoryBrandDTO brand;

    final String createdAt;

    final Long deliveryTypeId;

    final Long id;

    final String orderInvoiceNumber;

    final String orderNumber;

    final OrderHistoryOrigenDTO origin;

    final Double total;

    final OrderHistoryStatusDTO status;

    final UserHistoryDTO user;

}
