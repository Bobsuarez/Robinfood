package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDTO {

    private final OrderFiscalIdentifierDTO buyer;

    private final Long brandId;

    private final String brandName;

    private final BigDecimal co2Total;

    private final String currency;

    private final List<CouponDTO> coupons;

    private final Long id;

    private final Double discount;

    private final List<OrderDetailDiscountDTO> discounts;

    private final Long deliveryTypeId;

    private final BigDecimal deduction;

    private final ElectronicBillDTO electronicBill;

    private final Long flowId;

    private final BigDecimal foodCoins;

    private final String invoice;

    private final String notes;

    private final Long originId;

    private final String originName;

    private final String orderNumber;

    private final String orderUuid;

    private final String orderIntegrationId;

    private final String orderIntegrationUser;

    private final Boolean orderIsIntegration;

    private final String orderIntegrationCode;

    private final String operationDate;

    private final String operationTime;

    private final List<OrderDetailPaymentMethodDTO> paymentMethods;

    private final Long posId;

    private final String posResolutionPrefix;

    private final Boolean printed;

    private final List<OrderDetailProductDTO> products;

    private final List<OrderDetailServiceDTO> services;

    private final Long statusId;

    private final String statusName;

    private final Long storeId;

    private final String storeName;

    private final Double subtotal;

    private final Double tax;

    private final Double total;

    private final Long transactionId;

    private final String transactionUuid;

    private final String uid;

    private final OrderDetailUserDTO user;

    private final List<OrderDetailUserDTO> users;
}
