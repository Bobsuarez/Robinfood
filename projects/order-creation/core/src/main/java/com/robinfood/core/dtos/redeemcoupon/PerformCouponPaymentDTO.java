package com.robinfood.core.dtos.redeemcoupon;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class PerformCouponPaymentDTO implements Serializable {

    private final Long paymentMethodId;

    private final List<RedeemCouponPaymentMethodDTO> paymentMethods;

    private final BigDecimal total;
}
