package com.robinfood.repository.coupons

import com.robinfood.core.dtos.coupons.CouponValidationResponseDTO
import com.robinfood.core.entities.coupons.CouponValidationRequestEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.coupons.toCouponValidationResponseDTO
import com.robinfood.network.api.CouponsAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of ICouponsRepository
 */
@Repository
class CouponsRepository(
        private val couponsAPI: CouponsAPI,
        private val dispatchers: DispatcherProvider
) : ICouponsRepository {
    /**
     * Validates a coupon to be redeemed
     * [token] the authentication token to be used
     * [couponValidationRequestEntity] the coupon validation body to make request
     * @return coupon validation response
     */
    override suspend fun validateCoupon(
            token: String,
            couponValidationRequestEntity: CouponValidationRequestEntity
    ): Result<CouponValidationResponseDTO> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                    call = {
                        couponsAPI.validateCoupon(token, couponValidationRequestEntity).callServices()
                    }
            )

            when (result) {
                is Result.Success -> {
                    val couponValidationResponseEntity = result.data.data
                    if (couponValidationResponseEntity == null) {
                        Result.Error(
                                OrchestratorException("Coupon validation response is null"),
                                HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val couponValidationResponseDTO = couponValidationResponseEntity.toCouponValidationResponseDTO()
                        Result.Success(couponValidationResponseDTO)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}