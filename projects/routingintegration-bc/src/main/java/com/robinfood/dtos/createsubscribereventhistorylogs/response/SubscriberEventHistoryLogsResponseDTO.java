package com.robinfood.dtos.createsubscribereventhistorylogs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SubscriberEventHistoryLogsResponseDTO {

    private Long flowEventLogId;
    private Integer eventDispatched;
    private Long id;
    private Long subscriberId;
    private String uuid;
}
