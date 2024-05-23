package com.robinfood.core.entities.redeemcoupon;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PerformCouponResponseEntity {

    private String code;

    private Long codeId;

    private Long couponType;

    private BigDecimal discount;

    @JsonProperty("redeemed_id")
    private String redeemedId;
}
