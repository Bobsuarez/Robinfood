package org.example.dtos.geteventflow.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEventFlowDTO {

    private String eventId;

    private Long id;

    private Long flowId;

    private String payload;

    private String uuid;
}