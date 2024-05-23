package com.robinfood.core.entities.redeemcoupon;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PerformCouponEntity {

    private Long id;

    private String reference;

    private Long source;
}
