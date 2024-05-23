package com.robinfood.repository.mocks.entities;

import com.robinfood.core.entities.FoodCoinsEntityRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;

import java.math.BigDecimal;

public class RedeemFoodCoinsRequestEntityMocks {

    public static RedeemFoodCoinsRequestEntity getDataDefault() {

        return RedeemFoodCoinsRequestEntity.builder()
                .amount(BigDecimal.valueOf(8900))
                .countryId(1L)
                .entity(FoodCoinsEntityRequestEntityMocks.getDataDefault())
                .extra1(1L)
                .extra2("muy 79")
                .issuerId(1L)
                .issuerName("muy")
                .operationType(1)
                .sourceId(1L)
                .userId(1L)
                .build();
    }
}
