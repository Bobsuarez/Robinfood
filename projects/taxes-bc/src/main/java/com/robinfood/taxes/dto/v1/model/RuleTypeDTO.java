package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RuleTypeDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = 7788124677057479571L;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String identifier;
}
