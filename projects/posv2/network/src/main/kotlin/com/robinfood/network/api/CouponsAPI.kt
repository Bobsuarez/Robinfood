package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.coupons.CouponValidationRequestEntity
import com.robinfood.core.entities.coupons.CouponValidationResponseEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Interface provided to make coupons orchestrator api calls using Retrofit
 */
interface CouponsAPI {

    /**
     * Connects to an endpoint and validates a coupon to be redeemed
     *
     * @param token the authorization token
     */
    @POST("v2/coupons/codes/validate")
    suspend fun validateCoupon(
            @Header("Authorization") token: String,
            @Body couponValidationRequestEntity: CouponValidationRequestEntity
    ): Response<APIResponseEntity<CouponValidationResponseEntity>>
}