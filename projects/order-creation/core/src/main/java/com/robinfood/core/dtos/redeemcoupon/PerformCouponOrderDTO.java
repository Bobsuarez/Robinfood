package com.robinfood.core.dtos.redeemcoupon;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class PerformCouponOrderDTO implements Serializable {

    private final Long flowId;

    private final Long orderId;

    private final List<RedeemCouponProductDTO> products;
}
