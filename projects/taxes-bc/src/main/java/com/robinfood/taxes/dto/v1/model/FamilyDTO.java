package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -8363978174483252835L;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "country_id")
    private Long countryId;

    @JsonProperty(value = "family_type_id")
    private Long familyTypeId;

    @JsonProperty(value = "status")
    private Integer status;
}
