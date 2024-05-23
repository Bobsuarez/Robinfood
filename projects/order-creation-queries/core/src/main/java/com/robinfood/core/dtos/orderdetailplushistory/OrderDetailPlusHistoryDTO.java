package com.robinfood.core.dtos.orderdetailplushistory;

import com.robinfood.core.dtos.CouponDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailUserDTO;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderDetailPlusHistoryDTO {

    private final String brandName;

    private final BigDecimal co2Total;

    private final List<CouponDTO> coupons;

    private final Double discount;

    private final String invoice;

    private final List<StatusOrderHistoryDTO> orderHistory;

    private final String originName;

    private final String orderIntegrationId;

    private final Boolean orderIsIntegration;

    private final String operationDate;

    private final String operationTime;

    private final List<OrderDetailPaymentMethodSummaryDTO> paymentMethods;

    private final String posResolutionPrefix;

    private final List<OrderDetailProductDTO> products;

    private final List<OrderServicesDetailsSummaryDTO> services;

    private final String statusCode;

    private final String storeName;

    private final Double subtotal;

    private final Double tax;

    private final Double total;

    private final BigDecimal totalPlusCo2;

    private final OrderDetailUserDTO user;
}
