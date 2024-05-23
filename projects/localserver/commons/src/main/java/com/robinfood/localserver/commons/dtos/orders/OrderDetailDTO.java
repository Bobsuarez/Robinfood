package com.robinfood.localserver.commons.dtos.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDTO {

    private OrderBuyerDTO buyer;

    private Long brandId;

    private String brandName;

    private BigDecimal co2Total;

    private String currency;

    private List<CouponsDTO> coupons;

    private Long id;

    private BigDecimal deduction;

    private BigDecimal discount;

    private List<OrderDetailDiscountDTO> discounts;

    private Long deliveryTypeId;

    private Long flowId;

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

    private List<OrderDetailPaymentMethodDTO> paymentMethods;

    private Long posId;

    private Boolean printed;

    private List<OrderDetailProductDTO> products;

    private List<ServiceDTO> services;

    private Long statusId;

    private String statusName;

    private Long storeId;

    private String storeName;

    private Double subtotal;

    private Double tax;

    private BigDecimal total;

    private Long transactionId;

    private String uid;

    private OrderDetailUserDTO user;

    private List<OrderDetailUserDTO> users;
}
