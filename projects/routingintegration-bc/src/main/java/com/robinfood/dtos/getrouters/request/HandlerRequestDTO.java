package com.robinfood.dtos.getrouters.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HandlerRequestDTO {

    @JsonProperty("code")
    private String flowCode;

    @JsonProperty("id")
    private Long channelId;


}
