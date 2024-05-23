package com.robinfood.core.mappers;

import com.robinfood.core.dtos.CouponDTO;
import com.robinfood.core.entities.CouponEntity;

public final class CouponMappers {

    private CouponMappers() {
        // this constructor is empty because it is a utility class
    }

    public static CouponDTO toCouponsDTO(CouponEntity couponEntity) {

        return CouponDTO.builder()
                .value(couponEntity.getValue())
                .code(couponEntity.getCode())
                .build();
    }
}
