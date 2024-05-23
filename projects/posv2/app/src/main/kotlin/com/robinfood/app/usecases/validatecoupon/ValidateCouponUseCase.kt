package com.robinfood.app.usecases.validatecoupon

import com.robinfood.core.dtos.coupons.CouponValidationRequestDTO
import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.mappers.coupons.toCouponValidationRequestEntity
import com.robinfood.repository.coupons.ICouponsRepository

/**
 * Implementation of IValidateCouponUseCase
 */
class ValidateCouponUseCase(
    private val couponsRepository: ICouponsRepository
) : IValidateCouponUseCase {
    /**
     * Sends a request to validate a coupon
     * @param token the authentication token
     * @param couponValidationRequestDTO the json body request with the coupon info
     * @return the coupon validation response
     */
    override suspend fun invoke(
            token: String,
            couponValidationRequestDTO: CouponValidationRequestDTO
    ): Result<CouponValidationResponseDTO> {
        return couponsRepository.validateCoupon(
                token,
                couponValidationRequestDTO.toCouponValidationRequestEntity()
        )
    }
}
