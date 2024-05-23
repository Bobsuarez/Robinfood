package com.robinfood.core.entities.redeemcoupon;

import java.util.List;
import lombok.Data;

@Data
public class RedeemCouponProductEntity {

    private final Long brandId;

    private final Long id;

    private final List<RedeemCouponPortionEntity> portions;
}
