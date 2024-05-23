package com.robinfood.repository.mocks.entities;

import com.robinfood.core.entities.FoodCoinsEntityRequestEntity;

public class FoodCoinsEntityRequestEntityMocks {

    public static FoodCoinsEntityRequestEntity getDataDefault() {

        return FoodCoinsEntityRequestEntity.builder()
                .id(1L)
                .reference("abc")
                .source(1L)
                .build();
    }
}
