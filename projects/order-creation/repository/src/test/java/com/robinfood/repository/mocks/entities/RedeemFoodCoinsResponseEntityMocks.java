package com.robinfood.repository.mocks.entities;

import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;

import java.math.BigDecimal;

public class RedeemFoodCoinsResponseEntityMocks {

    public static RedeemFoodCoinsResponseEntity getDataDefault() {

        return RedeemFoodCoinsResponseEntity.builder()
                .amount(BigDecimal.valueOf(-100))
                .currentCredits(106722)
                .uuid("c3018f801816d4b72aa5c34a69df33")
                .build();
    }
}
