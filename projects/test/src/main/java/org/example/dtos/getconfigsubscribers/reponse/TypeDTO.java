package org.example.dtos.getconfigsubscribers.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TypeDTO {

    private String description;

    private String name;

    @JsonProperty("id")
    private Long typeId;
}