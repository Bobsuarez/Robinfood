package com.robinfood.core.dtos.redeemcoupon;

import lombok.Data;

import java.io.Serializable;

@Data
public class PerformCouponOriginDTO implements Serializable {

    private final Long companyId;

    private final String ip;

    private final Long platformId;

    private final String platformVersion;

    private final Long storeId;

    private final String timezone;
}
