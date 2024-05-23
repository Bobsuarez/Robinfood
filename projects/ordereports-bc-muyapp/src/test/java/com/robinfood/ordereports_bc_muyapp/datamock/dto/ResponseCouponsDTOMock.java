package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseCouponsDTO;

import java.math.BigDecimal;

public class ResponseCouponsDTOMock {

    public static ResponseCouponsDTO getDataDefault() {

        return ResponseCouponsDTO.builder()
                .value(new BigDecimal("110.0"))
                .code("Coupon")
                .build();
    }
}
