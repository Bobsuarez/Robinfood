package com.robinfood.core.entities;

import com.robinfood.core.entities.services.OriginEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDailyEntity {

    private final Long brandId;

    private final String createdAt;

    private final Long deliveryTypeId;

    private final Long id;

    private final String orderInvoiceNumber;

    private final String orderNumber;

    private final OriginEntity origin;

    private final Double total;

    private final OrderDetailUserEntity user;
}
