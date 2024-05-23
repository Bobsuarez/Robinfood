package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDailyDTO {

    private BrandDTO brand;

    private final Long brandId;

    private final String createdAt;

    private final Long deliveryTypeId;

    private final Long id;

    private final String orderInvoiceNumber;

    private final String orderNumber;

    private final OriginDTO origin;

    private final Double total;

    private final OrderDetailUserDTO user;
}
