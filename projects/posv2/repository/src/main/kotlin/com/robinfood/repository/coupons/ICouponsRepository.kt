package com.robinfood.repository.coupons

import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import com.robinfood.core.entities.coupons.CouponValidationRequestEntity
import com.robinfood.core.enums.Result

/**
 * Repository for coupons related data
 */
interface ICouponsRepository {

    /**
     * Validates a coupon to be redeemed
     * [token] the authentication token to be used
     * [couponValidationRequestEntity] the coupon validation body to make request
     * @return coupon validation response
     */
    suspend fun validateCoupon(
            token: String,
            couponValidationRequestEntity: CouponValidationRequestEntity
    ): Result<CouponValidationResponseDTO>
}