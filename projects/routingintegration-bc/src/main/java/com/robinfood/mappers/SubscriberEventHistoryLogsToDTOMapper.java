package com.robinfood.mappers;

import com.robinfood.dtos.createsubscribereventhistorylogs.response.SubscriberEventHistoryLogsResponseDTO;
import com.robinfood.entities.SubscriberEventHistoryLogsEntity;

public class SubscriberEventHistoryLogsToDTOMapper {

    public static SubscriberEventHistoryLogsResponseDTO buildToSubscriberEventHistoryLogsResponseDTO(
            SubscriberEventHistoryLogsEntity subscriberEventHistoryLogsEntity,
            String uuid
    ) {
        return SubscriberEventHistoryLogsResponseDTO.builder()
                .eventDispatched(subscriberEventHistoryLogsEntity.getEvent_dispatched())
                .flowEventLogId(subscriberEventHistoryLogsEntity.getFlow_event_log_id())
                .id(subscriberEventHistoryLogsEntity.getId())
                .subscriberId(subscriberEventHistoryLogsEntity.getSubscriber_id())
                .uuid(uuid)
                .build();
    }
}
