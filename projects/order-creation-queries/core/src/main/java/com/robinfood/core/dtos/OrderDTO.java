package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OrderDTO {

    private final Long billingResolutionId;

    private final Long brandId;

    private final String brandName;

    private final Long companyId;

    private final String createdAt;

    private final String currency;

    private final Long deliveryTypeId;

    private final Double discounts;

    private final Long id;

    private final String localDate;

    private final String localTime;

    private final Integer numberFinalProducts;

    private final String operationDate;

    private final String orderInvoiceNumber;

    private final String orderNumber;

    private final Long originId;

    private final String originName;

    private final Boolean paid;

    private Long paymentModelId;

    private final String pickupTime;

    private final Long posId;

    private final Boolean printed;

    private final Long statusId;

    private final Long storeId;

    private final String storeName;

    private final Double subtotal;

    private final Double taxes;

    private final Long transactionId;

    private final BigDecimal co2Total;

    private final Double total;

    private final String uid;

    private final String uuid;

    private final Long userId;

    private final Long workshiftId;
}
