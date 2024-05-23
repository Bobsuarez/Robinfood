package com.robinfood.core.dtos.redeemcoupon;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PerformCouponEntityDTO {

    private Long id;

    private String reference;

    private Long source;
}
