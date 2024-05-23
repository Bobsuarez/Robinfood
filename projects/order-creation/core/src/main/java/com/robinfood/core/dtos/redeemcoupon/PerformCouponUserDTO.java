package com.robinfood.core.dtos.redeemcoupon;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PerformCouponUserDTO implements Serializable {

    private final String firstName;

    private final String lastName;

    private final Integer numberOrdersCompleted;

    private final String phoneCode;

    private final String phoneNumber;

    private final Long userId;

    private final String email;

    private final Boolean isEmployee;

    private final List<PerformCouponUserDTO.Counter> counters;

    @Builder
    @Data
    public static class Counter {
        private String key;

        private Long reference;

        private Long value;
    }
}
