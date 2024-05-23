package com.robinfood.repository.coupons;

import com.robinfood.core.entities.ConfigRedeemCouponEntity;

public class CouponSystemLocalDataSource implements ICouponSystemLocalDataSource {

    private ConfigRedeemCouponEntity privateConfigRedeemCouponEntity;

    @Override
    public ConfigRedeemCouponEntity getRedeemCouponRedeemIds() {
        return privateConfigRedeemCouponEntity;
    }

    @Override
    public void setRedeemCouponRedeemIds(ConfigRedeemCouponEntity privateConfigRedeemCouponEntity) {
        this.privateConfigRedeemCouponEntity = privateConfigRedeemCouponEntity;
    }
}
