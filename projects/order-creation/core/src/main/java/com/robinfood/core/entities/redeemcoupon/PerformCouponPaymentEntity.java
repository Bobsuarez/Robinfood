package com.robinfood.core.entities.redeemcoupon;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class PerformCouponPaymentEntity {

    private final Long paymentMethodId;

    private final List<RedeemCouponPaymentMethodEntity> paymentMethods;

    private final BigDecimal total;
}
