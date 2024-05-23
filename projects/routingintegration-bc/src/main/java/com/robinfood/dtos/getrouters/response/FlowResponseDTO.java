package com.robinfood.dtos.getrouters.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowResponseDTO {

    private String code;

    private Long flowId;

    private String name;
}
