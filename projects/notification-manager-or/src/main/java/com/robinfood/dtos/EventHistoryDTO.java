package com.robinfood.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EventHistoryDTO {

    private Long eventDispatched;
    private Long flowEventLogId;
    private Long subscriberId;
}
