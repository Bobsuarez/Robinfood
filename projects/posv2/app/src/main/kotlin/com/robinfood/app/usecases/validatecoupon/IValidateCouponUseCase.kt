package com.robinfood.app.usecases.validatecoupon

import com.robinfood.core.dtos.coupons.CouponValidationRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case that validates a coupon
 */
interface IValidateCouponUseCase {

    /**
     * Sends a request to validate a coupon
     * @param token the authentication token
     * @param couponValidationRequestDTO the json body request with the coupon info
     * @return the coupon validation response
     */
    suspend fun invoke(
            token: String,
            couponValidationRequestDTO: CouponValidationRequestDTO
    ): Result<CouponValidationResponseDTO>
}