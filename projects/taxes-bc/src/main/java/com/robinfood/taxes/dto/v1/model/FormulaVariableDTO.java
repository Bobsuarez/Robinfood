package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormulaVariableDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = 3876077570366282634L;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "value")
    private String value;

}
