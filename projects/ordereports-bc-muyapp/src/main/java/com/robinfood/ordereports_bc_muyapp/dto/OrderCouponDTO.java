package com.robinfood.ordereports_bc_muyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class OrderCouponDTO {

    @NonNull
    private String code;

    private Short couponType;

    private String redeemedId;

    private Long transactionId;

    private BigDecimal value;
}
