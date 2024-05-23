package com.robinfood.datamock;

import com.robinfood.entities.SubscriberChannelsEntity;

public class SubscribersChannelsEntityMock {

    public static SubscriberChannelsEntity build() {
        return SubscriberChannelsEntity.builder()
                .channel_id(1L)
                .subscriber_id(1L)
                .id(1L)
                .flow_id(1L)
                .build();
    }
}
