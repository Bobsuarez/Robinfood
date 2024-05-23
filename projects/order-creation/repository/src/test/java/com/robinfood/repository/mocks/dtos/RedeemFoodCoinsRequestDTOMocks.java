package com.robinfood.repository.mocks.dtos;

import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;

import java.math.BigDecimal;

public class RedeemFoodCoinsRequestDTOMocks {

    public static RedeemFoodCoinsRequestDTO getDataDefault() {

        return RedeemFoodCoinsRequestDTO.builder()
                .amount(BigDecimal.valueOf(8900))
                .countryId(1L)
                .entity(FoodCoinsEntityRequestDTOMocks.getDataDefault())
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
