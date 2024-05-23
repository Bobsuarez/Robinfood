package com.robinfood.datamock;

import com.robinfood.entities.SubscriberTypesEntity;

public class SubscriberTypeEntityMock {

    public static SubscriberTypesEntity build() {
        return SubscriberTypesEntity.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();
    }
}
