package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleVariableDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = 3745116474501003385L;

    @JsonProperty
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("path")
    private String path;

    @JsonProperty("value")
    private String value;

}
