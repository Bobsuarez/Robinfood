package com.robinfood.core.dtos.redeemcoupon;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class PerformCouponResponseDTO implements Serializable {

    private final String code;

    private final Long codeId;

    private final Long couponType;

    private final BigDecimal discount;

    private final String redeemedId;
}
