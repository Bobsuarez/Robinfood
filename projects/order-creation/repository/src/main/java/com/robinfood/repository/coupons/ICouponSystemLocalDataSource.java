package com.robinfood.repository.coupons;

import com.robinfood.core.entities.ConfigRedeemCouponEntity;

public interface ICouponSystemLocalDataSource {

    /**
     * Return the redeemed ids of the coupons
     * @return the list of necessary steps to create an order
     */

    ConfigRedeemCouponEntity getRedeemCouponRedeemIds();
    /**
     * Set the redeemed ids of the coupons
     * @param configRedeemCouponEntity the updated modules
     */
    void setRedeemCouponRedeemIds(ConfigRedeemCouponEntity configRedeemCouponEntity);
}
