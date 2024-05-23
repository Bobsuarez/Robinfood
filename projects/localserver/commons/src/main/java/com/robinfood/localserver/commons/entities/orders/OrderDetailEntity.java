package com.robinfood.localserver.commons.entities.orders;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailEntity {

    private OrderBuyerEntity buyer;

    private Long brandId;

    private String brandName;

    private BigDecimal co2Total;

    private Long id;

    private String currency;

    private Long flowId;

    private List<CouponEntity> coupons;

    private BigDecimal deduction;

    private BigDecimal discount;

    private List<OrderDetailDiscountEntity> discounts;

    private Long deliveryTypeId;

    private String invoice;

    private String notes;

    private Long originId;

    private String originName;

    private String orderNumber;

    private String orderIntegrationId;

    private String orderIntegrationUser;

    private String orderIntegrationCode;

    private Boolean orderIsIntegration;

    private String orderUuid;

    private List<OrderDetailPaymentMethodEntity> paymentMethods;

    private Long posId;

    private Boolean printed;

    private List<OrderDetailProductEntity> products;

    private List<ServiceEntity> services;

    private Long statusId;

    private String statusName;

    private Long storeId;

    private String storeName;

    private Double subtotal;

    private Double tax;

    private BigDecimal total;

    private Long transactionId;

    private String uid;

    private OrderDetailUserEntity user;

    private List<OrderDetailUserEntity> users;
}
