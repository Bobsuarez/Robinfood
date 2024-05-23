package com.robinfood.mappers.request;

import com.robinfood.dtos.createsubscribereventhistorylogs.request.SubscriberEventHistoryLogsRequestDTO;
import com.robinfood.entities.SubscriberEventHistoryLogsEntity;

public class SubscriberEventHistoryLogsRequestDTOToEntityMapper {

    public static SubscriberEventHistoryLogsEntity buildToSubscriberEventHistoryLogsEntity(
            SubscriberEventHistoryLogsRequestDTO requestDTO
    ) {
        return SubscriberEventHistoryLogsEntity.builder()
                .flow_event_log_id(requestDTO.getFlowEventLogId())
                .event_dispatched(requestDTO.getEventDispatched())
                .subscriber_id(requestDTO.getSubscriberId())
                .build();
    }
}
