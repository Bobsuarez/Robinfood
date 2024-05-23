package com.robinfood.core.mappers;

import com.robinfood.core.dtos.redeemcoupon.RevertCouponRequestDTO;
import com.robinfood.core.entities.redeemcoupon.RevertCouponRequestEntity;

public final class RevertCouponMapper {

    private RevertCouponMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static RevertCouponRequestEntity toRevertCouponRequestEntity(RevertCouponRequestDTO revertCouponRequestDTO) {
        return new RevertCouponRequestEntity(
                revertCouponRequestDTO.getCodeRedemeedIds()
        );
    }
}
