package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.AbstractBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StateDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -1234393082854020087L;

    @Schema(example = "Ciudad de Mexico")
    @JsonProperty(value = "name")
    private String name;

    private List<CityDTO> cityList;
}
