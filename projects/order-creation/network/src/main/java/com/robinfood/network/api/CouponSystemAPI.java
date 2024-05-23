package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponRequestEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.entities.redeemcoupon.RevertCouponRequestEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Connections to Coupon System Business Capability
 */
public interface CouponSystemAPI {

    /**
     * Connects to an endpoint and returns the redemption of a coupon
     * @param token the authorization token
     * @param performCouponRequestEntity request body with order and coupon info
     * @return a future containing the redeem coupon information
     */
    @POST("v2/coupons/codes/redeem")
    Call<ApiResponseEntity<PerformCouponResponseEntity>> redeemCoupon(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body PerformCouponRequestEntity performCouponRequestEntity
    );

    /**
     * Connects to an endpoint and returns the revert of a coupon applied
     *
     * @param token the authorization token
     * @param revertCouponRequestEntity request body with redeemed ids to revert
     * @return a future containing the redeemed ids reverted
     */
    @POST("v2/coupons/codes/revert")
    Call<ApiResponseEntity<Object>> revertCoupon(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body RevertCouponRequestEntity revertCouponRequestEntity
    );

    /**
     * Validates a coupon and returns its value
     * @param token the authorization token
     * @param performCouponRequestEntity request body with redeemed ids to validate
     * @return the information of a certain coupon
     */
    @POST("v2/coupons/codes/validate")
    Call<ApiResponseEntity<PerformCouponResponseEntity>> validateCoupon(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body PerformCouponRequestEntity performCouponRequestEntity
    );

}
