package com.robinfood.core.dtos.redeemcoupon;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class PerformCouponRequestDTO implements Serializable {

    private final String code;

    private final PerformCouponEntityDTO entity;

    private final PerformCouponOrderDTO order;

    private final PerformCouponOriginDTO origin;

    private final PerformCouponPaymentDTO payment;

    private final PerformCouponUserDTO user;
}
