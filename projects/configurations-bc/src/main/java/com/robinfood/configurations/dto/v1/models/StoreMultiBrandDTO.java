package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreMultiBrandDTO implements Serializable {

    private static final long serialVersionUID = 3311809113877800008L;

    @JsonProperty(value = "image")
    private String image;

    @JsonProperty(value = "color")
    private String color;
}
