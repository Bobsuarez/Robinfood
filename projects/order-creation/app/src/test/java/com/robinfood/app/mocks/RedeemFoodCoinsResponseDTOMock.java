package com.robinfood.app.mocks;

import com.robinfood.core.dtos.RedeemFoodCoinsResponseDTO;

import java.math.BigDecimal;

public class RedeemFoodCoinsResponseDTOMock {

    public static RedeemFoodCoinsResponseDTO build() {
        return RedeemFoodCoinsResponseDTO.builder()
                .amount(BigDecimal.valueOf(4000))
                .currentCredits(106722)
                .uuid("c3018f801816d4b72aa5c34a69df33")
                .build();
    }
}
