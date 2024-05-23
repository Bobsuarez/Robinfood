package org.example.dtos.getconfigsubscribers.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PropertiesItemDTO {

    private String description;

    private String key;

    private String name;

    @JsonProperty("id")
    private Long propertiesId;
}