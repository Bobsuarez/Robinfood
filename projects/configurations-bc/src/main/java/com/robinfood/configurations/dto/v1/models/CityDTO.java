package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.AbstractBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CityDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -5952393082004027787L;

    @JsonProperty(value = "code")
    private String code;

    @Schema(example = "Bogota")
    @JsonProperty(value = "name")
    private String name;

    @Schema(example = "America/Bogota")
    @JsonProperty(value = "timezone")
    private transient String timezone;

}
