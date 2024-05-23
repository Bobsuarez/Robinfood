package com.robinfood.core.dtos.redeemcoupon;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class RedeemCouponPaymentMethodDTO  implements Serializable {

    private final Long id;

    private final BigDecimal value;
}
