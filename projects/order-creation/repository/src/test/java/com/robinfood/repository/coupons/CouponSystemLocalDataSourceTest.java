package com.robinfood.repository.coupons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.core.entities.ConfigRedeemCouponEntity;
import java.util.List;
import org.junit.jupiter.api.Test;

class CouponSystemLocalDataSourceTest {

    @Test
    void test_CouponSystemLocalDataSource() {

        List<Long> ids = List.of(10L, 11L);
        CouponSystemLocalDataSource couponSystemLocalDataSource = new CouponSystemLocalDataSource();

        couponSystemLocalDataSource.setRedeemCouponRedeemIds(new ConfigRedeemCouponEntity(ids));

        ConfigRedeemCouponEntity configRedeemCouponEntity = couponSystemLocalDataSource.getRedeemCouponRedeemIds();

        assertEquals(ids, configRedeemCouponEntity.getRedeemIds());
    }
}
