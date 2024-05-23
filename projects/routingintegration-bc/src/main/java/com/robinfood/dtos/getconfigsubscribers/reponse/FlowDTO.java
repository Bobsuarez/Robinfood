package com.robinfood.dtos.getconfigsubscribers.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowDTO {

    private String code;

    @JsonProperty("id")
    private Long flowId;

    private String name;
}