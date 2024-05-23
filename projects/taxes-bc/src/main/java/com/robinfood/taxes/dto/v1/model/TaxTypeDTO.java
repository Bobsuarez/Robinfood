package com.robinfood.taxes.dto.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.taxes.dto.v1.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxTypeDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -5953533331123501276L;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "country_id")
    private Long countryId;

    @JsonProperty(value = "status")
    private int status;
}
