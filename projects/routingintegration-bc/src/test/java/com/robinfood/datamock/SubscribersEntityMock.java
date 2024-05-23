package com.robinfood.datamock;

import com.robinfood.entities.SubscribersEntity;

public class SubscribersEntityMock {

    public static SubscribersEntity build() {
        return SubscribersEntity.builder()
                .id(1L)
                .name("name")
                .description("description")
                .subscriber_type_id(1L)
                .build();
    }
}
