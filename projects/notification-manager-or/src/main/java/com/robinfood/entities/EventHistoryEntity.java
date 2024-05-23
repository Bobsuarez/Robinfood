package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EventHistoryEntity {

    private Long eventDispatched;
    private Long flowEventLogId;
    private Long subscriberId;
    private String uuid;
}
