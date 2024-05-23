package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDailyDTO {

    private final Long brandId;

    private final LocalDateTime createdAt;

    private final Long deliveryTypeId;

    private final Long id;

    private final String orderInvoiceNumber;

    private final String orderNumber;

    private final OriginDTO origin;

    private final Double total;

    private final UserDataDTO user;

}
