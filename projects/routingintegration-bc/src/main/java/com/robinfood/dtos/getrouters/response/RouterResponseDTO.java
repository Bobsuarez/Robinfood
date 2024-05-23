package com.robinfood.dtos.getrouters.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterResponseDTO {

    private Long channelId;

    private String description;

    @JsonProperty("flow")
    private FlowResponseDTO flowResponseDTO;

    @JsonProperty("id")
    private Long idRouter;

    private String name;

    private String url;

    private String uuid;

}
