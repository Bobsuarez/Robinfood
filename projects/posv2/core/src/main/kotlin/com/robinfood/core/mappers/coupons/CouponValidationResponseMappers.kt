package com.robinfood.core.mappers.coupons

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import com.robinfood.core.entities.coupons.CouponValidationResponseEntity
import java.math.BigDecimal

fun CouponValidationResponseEntity.toCouponValidationResponseDTO(): CouponValidationResponseDTO {
    return CouponValidationResponseDTO(
            codeId = codeId ?: DEFAULT_LONG_VALUE,
            discount = discount ?: BigDecimal.ZERO,
            total = total ?: BigDecimal.ZERO,
            couponType = couponType ?: DEFAULT_LONG_VALUE
    )
}
