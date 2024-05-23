package com.robinfood.core.entities.redeemcoupon;

import lombok.Data;

@Data
public class PerformCouponOriginEntity {

    private final Long companyId;

    private final String ip;

    private final Long platformId;

    private final String platformVersion;

    private final Long storeId;

    private final String timezone;
}
