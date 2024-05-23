package com.robinfood.core.dtos.storeorder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
public class StoreOrderDTO {

    private BigDecimal compensation;

    private Double discounts;

    private Double grossValue;

    private Long id;

    private Double netValue;

    private String orderInvoiceNumber;

    private Long posId;

    private String statusCode;

    private Long statusId;

    private Double taxes;

    private String uuid;
}
