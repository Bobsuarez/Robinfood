package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.AbstractBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CountryDTO extends AbstractBaseDTO implements Serializable {

    private static final long serialVersionUID = -1234393082855527787L;

    @Schema(example = "Colombia")
    @JsonProperty(value = "name")
    private String name;

    public CountryDTO(Long id) {
        super(id);
    }
}
