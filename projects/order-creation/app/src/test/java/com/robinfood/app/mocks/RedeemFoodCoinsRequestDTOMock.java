package com.robinfood.app.mocks;

import com.robinfood.core.dtos.FoodCoinsEntityRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;

import java.math.BigDecimal;

public class RedeemFoodCoinsRequestDTOMock {

    public static RedeemFoodCoinsRequestDTO build() {
        return RedeemFoodCoinsRequestDTO.builder()
                .amount(BigDecimal.valueOf(4000))
                .countryId(1L)
                .entity(FoodCoinsEntityRequestDTO.builder().build())
                .extra1(1L)
                .extra2("Robinfood")
                .issuerId(1L)
                .issuerName("Store test")
                .operationType(1)
                .sourceId(1L)
                .userId(1L)
                .build();
    }
}
