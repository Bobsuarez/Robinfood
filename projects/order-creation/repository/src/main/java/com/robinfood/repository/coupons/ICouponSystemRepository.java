package com.robinfood.repository.coupons;

import com.robinfood.core.dtos.ConfigRedeemCouponDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponRequestDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.redeemcoupon.RevertCouponRequestDTO;

/**
 * Repository for Coupon System
 */
public interface ICouponSystemRepository {

    /**
     * Return the redeemIds dto stored
     *
     * @return the redeemIds dto
     */

    ConfigRedeemCouponDTO getRedeemCouponRedeemIds();

    /**
     * Redeem the coupon in the order
     *
     * @param token                   the authorization token
     * @param performCouponRequestDTO request body for redeem coupon
     * @return a future containing the result of the operation
     */
    PerformCouponResponseDTO performCouponRequest(
            String token,
            PerformCouponRequestDTO performCouponRequestDTO
    );

    /**
     * Revert the coupon Redeemed
     *
     * @param token                  the authorization token
     * @param revertCouponRequestDTO request body for revert redeemed coupon
     * @return a future containing the result of the operation
     */
    Object revertRedeemIds(
            String token,
            RevertCouponRequestDTO revertCouponRequestDTO
    );

    /**
     * Store the redeemIds dto redeemed
     *
     * @param configRedeemCouponDTO contains the redeemIds
     */
    void setRedeemCouponRedeemIds(ConfigRedeemCouponDTO configRedeemCouponDTO);
}
