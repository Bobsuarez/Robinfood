package com.robinfood.dtos.createeventflow.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowEventLogsResponseDTO {

    private final String eventId;
    private final Long flowId;
    private final Long id;
    private final String payload;
    private final String uuid;
}
