package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.AbstractBaseDTO;
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
public class CityDTOResponse extends AbstractBaseDTO {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "timezone")
    private String timezone;
    @JsonProperty(value = "state")
    private StateDTO state;
}
