package com.robinfood.core.entities.redeemcoupon;

import lombok.Data;

@Data
public class PerformCouponRequestEntity {

    private final String code;

    private final PerformCouponEntity entity;

    private final PerformCouponOrderEntity order;

    private final PerformCouponOriginEntity origin;

    private final PerformCouponPaymentEntity payment;

    private final PerformCouponUserEntity user;
}
