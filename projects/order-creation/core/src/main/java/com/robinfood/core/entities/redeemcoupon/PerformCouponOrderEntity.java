package com.robinfood.core.entities.redeemcoupon;

import java.util.List;
import lombok.Data;

@Data
public class PerformCouponOrderEntity {

    private final Long flowId;

    private final Long orderId;
    
    private final List<RedeemCouponProductEntity> products;
}
