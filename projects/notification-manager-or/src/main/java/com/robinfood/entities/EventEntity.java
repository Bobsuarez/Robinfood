package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EventEntity {

    private String eventId;
    private Long flowId;
    private Long id;
    private String payload;
    private String uuid;
}
