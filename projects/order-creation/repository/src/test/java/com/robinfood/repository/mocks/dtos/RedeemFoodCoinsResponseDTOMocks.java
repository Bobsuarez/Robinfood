package com.robinfood.repository.mocks.dtos;

import com.robinfood.core.dtos.RedeemFoodCoinsResponseDTO;

import java.math.BigDecimal;

public class RedeemFoodCoinsResponseDTOMocks {

    public static RedeemFoodCoinsResponseDTO getDataDefault() {

        return RedeemFoodCoinsResponseDTO.builder()
                .amount(BigDecimal.valueOf(-100))
                .currentCredits(106722)
                .uuid("c3018f801816d4b72aa5c34a69df33")
                .build();
    }
}
