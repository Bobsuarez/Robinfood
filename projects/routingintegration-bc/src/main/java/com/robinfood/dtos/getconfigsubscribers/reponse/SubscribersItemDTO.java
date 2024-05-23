package com.robinfood.dtos.getconfigsubscribers.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SubscribersItemDTO {

    private String description;

    private String name;

    private List<PropertiesItemDTO> properties;

    @JsonProperty("id")
    private Long subscriberId;

    private TypeDTO type;
}