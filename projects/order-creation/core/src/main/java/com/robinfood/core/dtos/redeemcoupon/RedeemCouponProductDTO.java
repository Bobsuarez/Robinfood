package com.robinfood.core.dtos.redeemcoupon;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class RedeemCouponProductDTO implements Serializable {

    private final Long brandId;

    private final Long id;

    private final List<RedeemCouponPortionDTO> portions;
}
