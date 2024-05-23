package com.robinfood.datamock.response;

import com.robinfood.dtos.createsubscribereventhistorylogs.response.SubscriberEventHistoryLogsResponseDTO;

public class SubscriberEventHistoryLogsResponseDTOMock {

    public static SubscriberEventHistoryLogsResponseDTO build() {
        return SubscriberEventHistoryLogsResponseDTO.builder()
                .eventDispatched(1)
                .flowEventLogId(1L)
                .subscriberId(1L)
                .build();
    }
}
