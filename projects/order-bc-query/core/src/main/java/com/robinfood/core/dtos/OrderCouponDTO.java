package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
public class OrderCouponDTO{

    @NonNull
    private String code;

    private Long couponType;

    private String redeemedId;

    private Long transactionId;

    @JsonProperty("discount")
    private BigDecimal value;
}
