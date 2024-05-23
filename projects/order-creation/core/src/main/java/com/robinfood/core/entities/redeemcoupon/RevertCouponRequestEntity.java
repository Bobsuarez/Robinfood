package com.robinfood.core.entities.redeemcoupon;

import java.util.List;
import lombok.Data;

@Data
public class RevertCouponRequestEntity {

    private final List<Long> codeRedemeedIds;
}
