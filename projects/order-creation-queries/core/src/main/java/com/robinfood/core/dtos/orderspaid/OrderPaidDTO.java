package com.robinfood.core.dtos.orderspaid;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderPaidDTO {

    private final Long brandId;

    private final BigDecimal discounts;

    private final Long id;

    private final String localDate;

    private final String localTime;

    private final String orderInvoiceNumber;

    private final String orderIdIntegration;

    private final OrderUserDataDTO orderUserData;

    private final Long originId;

    private final boolean paid;

    private final String posResolutionsPrefix;

    private final StatusOrderDTO status;

    private final Long storeId;

    private final BigDecimal subtotal;

    private final BigDecimal taxes;

    private final BigDecimal total;

    private final BigDecimal totalCo2;

    private final String uId;

    private final String uuId;
}
