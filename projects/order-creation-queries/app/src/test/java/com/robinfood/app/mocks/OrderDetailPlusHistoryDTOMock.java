package com.robinfood.app.mocks;

import com.robinfood.app.mocks.statusorderhistory.StatusOrderHistoryDTOMock;
import com.robinfood.core.dtos.CouponDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailUserDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPaymentMethodSummaryDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPlusHistoryDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderServicesDetailsSummaryDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailPlusHistoryDTOMock {

    public static OrderDetailPlusHistoryDTO getDataDefault() {

        return OrderDetailPlusHistoryDTO.builder()
                .brandName("MUY")
                .co2Total(BigDecimal.valueOf(0.0000))
                .coupons(List.of(CouponDTO.builder().build()))
                .discount(0.0)
                .invoice("143776")
                .orderHistory(List.of(StatusOrderHistoryDTOMock.getDataDefault()))
                .originName("RFApp")
                .orderIntegrationId(null)
                .orderIsIntegration(false)
                .operationDate("2023-04-27")
                .operationTime("12:03:57")
                .paymentMethods(List.of(OrderDetailPaymentMethodSummaryDTO.builder()
                                .id(1L)
                                .name("cash")
                                .value(14900.0)
                        .build()))
                .posResolutionPrefix("RM07")
                .products(List.of(OrderDetailProductDTO.builder().build()))
                .services(List.of(OrderServicesDetailsSummaryDTO.builder()
                                .id(159652L)
                                .name("Domicilios MU")
                                .quantity(1)
                                .unitPrice(1000.0)
                                .total(1000.0)
                        .build()))
                .statusCode("ORDER_PAID")
                .storeName("MUY 79")
                .subtotal(13796.2963)
                .tax(1103.7037)
                .total(14900.0)
                .totalPlusCo2(BigDecimal.valueOf(14900.0000))
                .user(OrderDetailUserDTO.builder().build())
                .build();
    }
}
