package com.robinfood.core.dtos.ordersstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderStoreDTO {

    private final Double compensation;

    private final Double discounts;

    private final Double grossValue;

    private final Long id;

    private final Double netValue;

    private final  String orderInvoiceNumber;

    private final Long posId;

    private final String statusCode;

    private final Long statusId;

    private final Double taxes;

    private final String uuid;
}