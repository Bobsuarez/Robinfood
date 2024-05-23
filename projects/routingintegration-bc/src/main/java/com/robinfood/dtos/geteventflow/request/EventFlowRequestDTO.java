package com.robinfood.dtos.geteventflow.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class EventFlowRequestDTO {

    @JsonProperty("code")
    private String flowCode;

    @JsonProperty("id")
    private String eventId;


}
