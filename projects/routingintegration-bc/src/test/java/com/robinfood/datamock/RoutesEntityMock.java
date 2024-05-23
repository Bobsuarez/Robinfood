package com.robinfood.datamock;

import com.robinfood.entities.RoutesEntity;

public class RoutesEntityMock {

    public static RoutesEntity build() {
        return RoutesEntity.builder()
                .channel_id(1L)
                .url("url")
                .channel_id(1L)
                .name("name")
                .description("description")
                .build();
    }
}
