package com.robinfood.core.dtos.redeemcoupon;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class RevertCouponRequestDTO implements Serializable {

    private final List<Long> codeRedemeedIds;
}
