package com.robinfood.core.entities.redeemcoupon;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PerformCouponUserEntity {

    private final String firstName;

    private final String lastName;

    private final Integer numberOrdersCompleted;

    private final String phoneCode;

    private final String phoneNumber;

    private final Long userId;

    private final String email;

    private final Boolean isEmployee;

    private final List<PerformCouponUserEntity.Counter> counters;

    @Builder
    @Data
    public static class Counter {
        private String key;

        private Long reference;

        private Long value;
    }
}
