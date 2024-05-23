package org.example.mappers.request;

import org.example.dtos.createsubscribereventhistorylogs.request.SubscriberEventHistoryLogsRequestDTO;
import org.example.entities.SubscriberEventHistoryLogsEntity;

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
