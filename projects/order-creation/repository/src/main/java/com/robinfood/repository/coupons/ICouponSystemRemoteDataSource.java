package com.robinfood.repository.coupons;

import com.robinfood.core.entities.redeemcoupon.PerformCouponRequestEntity;
import com.robinfood.core.entities.redeemcoupon.PerformCouponResponseEntity;
import com.robinfood.core.entities.redeemcoupon.RevertCouponRequestEntity;

/**
 * Remote data source for redeem coupons and revert coupons
 */
public interface ICouponSystemRemoteDataSource {

    /**
     * Redeem the coupon of the order
     *
     * @param token                      Authorization token
     * @param performCouponRequestEntity Request body of the redeem coupon
     * @return Response body with redeem id
     */
    PerformCouponResponseEntity performCouponRequest(
            String token,
            PerformCouponRequestEntity performCouponRequestEntity
    );

    /**
     * Revert the coupon of the order
     *
     * @param token                     Authorization token
     * @param revertCouponRequestEntity Request body of the coupon to revert
     * @return Response body with redeem id
     */
    Object revertRedeemIds(
            String token,
            RevertCouponRequestEntity revertCouponRequestEntity
    );
}
