package com.robinfood.datamock;

import com.robinfood.entities.SubscriberEventHistoryLogsEntity;

public class SubscriberEventHistoryLogsEntityMock {

    public static SubscriberEventHistoryLogsEntity build() {
        return SubscriberEventHistoryLogsEntity.builder()
                .event_dispatched(1)
                .flow_event_log_id(1L)
                .subscriber_id(1L)
                .build();
    }
}
