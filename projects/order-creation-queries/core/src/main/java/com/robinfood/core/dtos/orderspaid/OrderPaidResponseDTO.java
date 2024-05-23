package com.robinfood.core.dtos.orderspaid;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class OrderPaidResponseDTO {

    private final String brandName;

    private final BigDecimal discounts;

    private final Long id;

    private final String localDate;

    private final String localTime;

    private final String orderInvoiceNumber;

    private final String orderIdIntegration;

    private final OrderUserDataDTO orderUserData;

    private final String originName;

    private final String posResolutionsPrefix;

    private final String statusCode;

    private final String storeName;

    private final BigDecimal subtotal;

    private final BigDecimal taxes;

    private final BigDecimal total;

    private final BigDecimal totalCo2;

    private final BigDecimal totalPlusCo2;

    private final String uId;

    private final String uuId;
}
