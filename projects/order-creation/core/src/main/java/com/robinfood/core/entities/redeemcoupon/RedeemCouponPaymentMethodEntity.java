package com.robinfood.core.entities.redeemcoupon;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RedeemCouponPaymentMethodEntity {

    private final Long id;

    private final BigDecimal value;
}
