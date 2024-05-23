package com.robinfood.datamock.request;

import com.robinfood.dtos.createsubscribereventhistorylogs.request.SubscriberEventHistoryLogsRequestDTO;

public class SubscriberEventHistoryLogsRequestDTOMock {

    public static SubscriberEventHistoryLogsRequestDTO build() {
        return SubscriberEventHistoryLogsRequestDTO.builder()
                .eventDispatched(1)
                .flowEventLogId(1L)
                .subscriberId(1L)
                .build();
    }
}
