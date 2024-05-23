package com.robinfood.dtos.createsubscribereventhistorylogs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SubscriberEventHistoryLogsRequestDTO {

    private Long flowEventLogId;

    private Integer eventDispatched;

    private Long subscriberId;

    private String uuid;
}
