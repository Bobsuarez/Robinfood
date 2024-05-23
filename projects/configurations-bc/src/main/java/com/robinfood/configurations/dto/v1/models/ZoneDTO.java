package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZoneDTO implements Serializable {

    private static final long serialVersionUID = 6308333266356777918L;

    @Schema(example = "City")
    @JsonProperty(value = "city")
    private CityDTO city;

    @Schema(example = "Id")
    @JsonProperty(value = "id")
    private Long id;

}
