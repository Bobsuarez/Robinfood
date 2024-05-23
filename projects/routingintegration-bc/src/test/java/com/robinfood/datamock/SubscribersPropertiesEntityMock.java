package com.robinfood.datamock;

import com.robinfood.entities.SubscriberPropertiesEntity;

public class SubscribersPropertiesEntityMock {

    public static SubscriberPropertiesEntity build() {
        return SubscriberPropertiesEntity.builder()
                .subscriber_id(1L)
                .id(1L)
                .key("KEY")
                .value("Value")
                .description("description")
                .build();
    }
}
