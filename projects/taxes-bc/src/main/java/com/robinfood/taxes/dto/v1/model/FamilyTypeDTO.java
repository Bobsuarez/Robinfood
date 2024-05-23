package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyTypeDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -5378052075459551856L;

    @JsonProperty(value = "name")
    private String name;

}
