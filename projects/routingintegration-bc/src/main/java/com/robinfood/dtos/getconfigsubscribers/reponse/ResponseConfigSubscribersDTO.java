package com.robinfood.dtos.getconfigsubscribers.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseConfigSubscribersDTO {

    private FlowDTO flow;

    private List<SubscribersItemDTO> subscribers;

    @JsonProperty("id")
    private Long subscriberChannelId;

    private String uuid;
}