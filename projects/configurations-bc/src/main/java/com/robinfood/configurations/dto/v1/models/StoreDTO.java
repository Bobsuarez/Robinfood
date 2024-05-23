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
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = 3311809113877852284L;

    @Schema(example = "store 123")
    @JsonProperty(value = "name")
    private String name;

    @Schema(example = "Av. 20 # 33 - 12")
    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "city")
    private CityDTO city;
}
