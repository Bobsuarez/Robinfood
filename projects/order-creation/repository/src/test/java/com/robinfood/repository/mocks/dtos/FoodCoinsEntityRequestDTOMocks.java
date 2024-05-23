package com.robinfood.repository.mocks.dtos;

import com.robinfood.core.dtos.FoodCoinsEntityRequestDTO;

public class FoodCoinsEntityRequestDTOMocks {

    public static FoodCoinsEntityRequestDTO getDataDefault() {
        return FoodCoinsEntityRequestDTO.builder()
                .id(1L)
                .reference("abc")
                .source(1L)
                .build();
    }
}
